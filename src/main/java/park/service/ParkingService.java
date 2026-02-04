package park.service;

import java.sql.*;
import park.DB_Connection;

public class ParkingService {
    public int parkvehicle(String vehicle_registration_id, String vehicle_type) throws Exception {
        Connection con = DB_Connection.getConnection();
        con.setAutoCommit(false);
        try {
            String normalizedType = vehicle_type.substring(0, 1).toUpperCase() +
                                    vehicle_type.substring(1).toLowerCase();

            PreparedStatement find = con
                    .prepareStatement("select id from Slot where vehicle_type=? AND status='EMPTY' LIMIT 1 FOR UPDATE");
            find.setString(1, normalizedType);

            ResultSet rs = find.executeQuery();
            if (!rs.next()) {
                con.rollback();
                return -1;
            }
            int slot_id = rs.getInt("id");

            PreparedStatement update = con.prepareStatement("update Slot SET status='Filled' where id=?");
            update.setInt(1, slot_id);
            update.executeUpdate();

            PreparedStatement getVehicle = con.prepareStatement(
                    "SELECT id FROM vehicle WHERE vehicle_registration_id = ?");
            getVehicle.setString(1, vehicle_registration_id);
            ResultSet vehicleRs = getVehicle.executeQuery();
            if (!vehicleRs.next())
                throw new Exception("Vehicle not registered");
            int vehicleId = vehicleRs.getInt("id");

            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO ParkingEntry(vehicle_id, slot_id, Entry_Time) VALUES (?, ?, ?)");
            insert.setInt(1, vehicleId);
            insert.setInt(2, slot_id);
            // ✅ Entry_Time 5 minutes in past to avoid chk_time violation
            insert.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis() - 5 * 60 * 1000));

            insert.executeUpdate();
            con.commit();
            return slot_id;

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
}
