package park.service;

import java.sql.*;
import park.DB_Connection;

public class BillingService {

    public double exitvehicle(String licensePlate) throws Exception {
        Connection con = DB_Connection.getConnection();
        con.setAutoCommit(false);
        double fee = 0;
        try {
            // Find active parking entry
            PreparedStatement pst = con.prepareStatement(
                    "SELECT pe.id as pe_id, pe.slot_id, pe.Entry_Time " +
                    "FROM ParkingEntry pe " +
                    "JOIN vehicle v ON pe.vehicle_id = v.id " +
                    "WHERE v.License_Plate=? AND pe.Exit_Time IS NULL FOR UPDATE"
            );
            pst.setString(1, licensePlate);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                con.rollback();
                System.out.println("No active parking entry found for the given license plate.");
                return 0;
            }

            int parkingEntryId = rs.getInt("pe_id");
            int slotId = rs.getInt("slot_id");
            Timestamp entryTime = rs.getTimestamp("Entry_Time");

            // Calculate fee (simple: 10 per hour)
            long millis = System.currentTimeMillis() - entryTime.getTime();
            long hours = Math.max(1, millis / (1000 * 60 * 60));
            fee = hours * 10;

            // Update ParkingEntry
            PreparedStatement update = con.prepareStatement(
                    "UPDATE ParkingEntry SET Exit_Time=NOW(), Total_amount=? WHERE id=?"
            );
            update.setDouble(1, fee);
            update.setInt(2, parkingEntryId);
            update.executeUpdate();

            // Free the slot
            PreparedStatement slotUpdate = con.prepareStatement(
                    "UPDATE Slot SET status='EMPTY' WHERE id=?"
            );
            slotUpdate.setInt(1, slotId);
            slotUpdate.executeUpdate();

            con.commit();
            return fee;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
}
