package park.model;

import java.sql.*;
import park.DB_Connection;

public class ParkingLot {

    private String parkingName;
    private int total_car_slot;
    private int total_bike_slot;

    public ParkingLot() throws Exception {
    Connection con = DB_Connection.getConnection();
    con.setAutoCommit(false);

    try {
        // Lock any existing parking lot
        PreparedStatement check = con.prepareStatement(
            "SELECT id, parking_name, total_car_slot, total_bike_slot FROM parking_lot LIMIT 1 FOR UPDATE"
        );

        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            //int pid = rs.getInt(1);
            parkingName = rs.getString(2);
            total_car_slot = rs.getInt(3);
            total_bike_slot = rs.getInt(4);
        } else {
            // Insert new parking lot and get its id
            PreparedStatement insert = con.prepareStatement(
                "INSERT INTO parking_lot(parking_name, total_car_slot, total_bike_slot) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            insert.setString(1, "XYZ");
            insert.setInt(2, 200);
            insert.setInt(3, 150);
            insert.executeUpdate();

            ResultSet keyRs = insert.getGeneratedKeys();
            keyRs.next();
            int pid = keyRs.getInt(1);

            // Set totals before creating slots
            parkingName = "XYZ";
            total_car_slot = 200;
            total_bike_slot = 150;

            // Insert slots
            PreparedStatement slotInsert = con.prepareStatement(
                "INSERT INTO Slot(parking_id, slot_no, vehicle_type, status) VALUES (?,?,?, 'EMPTY')"
            );

            int slotNo = 1;
            for (int i = 0; i < total_car_slot; i++) {
                slotInsert.setInt(1, pid);
                slotInsert.setInt(2, slotNo++);
                slotInsert.setString(3, "Car");
                slotInsert.addBatch();
            }
            for (int i = 0; i < total_bike_slot; i++) {
                slotInsert.setInt(1, pid);
                slotInsert.setInt(2, slotNo++);
                slotInsert.setString(3, "Bike");
                slotInsert.addBatch();
            }
            slotInsert.executeBatch();
        }

        con.commit();
    } catch (Exception e) {
        con.rollback();
        throw e;
    } finally {
        con.setAutoCommit(true);
        con.close();
    }
}

}
