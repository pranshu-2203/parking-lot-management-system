package park;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import park.service.ParkingService;
import park.service.BillingService;
import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParkingSystemTest {

    private static ParkingService ps;
    private static BillingService bs;

    @BeforeAll
    static void setup() throws Exception {
        try (Connection con = DB_Connection.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate("DELETE FROM ParkingEntry");
            st.executeUpdate("DELETE FROM vehicle");
            st.executeUpdate("UPDATE Slot SET status='EMPTY'");
        }

        ps = new ParkingService();
        bs = new BillingService();

        // ✅ Insert test vehicles
        try (Connection con = DB_Connection.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "INSERT INTO vehicle(vehicle_type, License_Plate, vehicle_owner_name, vehicle_registration_id, driver_name, driver_license_id, vehicle_brand) VALUES (?, ?, ?, ?, ?, ?, ?)"
             )) {
            // Car
            pst.setString(1, "Car");
            pst.setString(2, "CAR100");
            pst.setString(3, "Test Owner");
            pst.setString(4, "CAR100");
            pst.setString(5, "Driver Name");
            pst.setString(6, "DL12345");
            pst.setString(7, "Brand");
            pst.executeUpdate();

            // Bike
            pst.setString(1, "Bike");
            pst.setString(2, "BIKE100");
            pst.setString(3, "Test Owner");
            pst.setString(4, "BIKE100");
            pst.setString(5, "Driver Name");
            pst.setString(6, "DL54321");
            pst.setString(7, "Brand");
            pst.executeUpdate();
        }
    }

    @Test
    @Order(1)
    void testParkCar() throws Exception {
        int slot = ps.parkvehicle("CAR100", "Car");
        assertTrue(slot > 0, "Car should be assigned a valid slot");
    }

    @Test
    @Order(2)
    void testParkBike() throws Exception {
        int slot = ps.parkvehicle("BIKE100", "Bike");
        assertTrue(slot > 0, "Bike should be assigned a valid slot");
    }

    @Test
    @Order(3)
    void testExitCarAndFee() throws Exception {
        double fee = bs.exitvehicle("CAR100");
        assertTrue(fee > 0, "Fee should be calculated for car");
    }

    @Test
    @Order(4)
    void testExitBikeAndFee() throws Exception {
        double fee = bs.exitvehicle("BIKE100");
        assertTrue(fee > 0, "Fee should be calculated for bike");
    }

    @Test
    @Order(5)
    void testSlotFreedAfterExit() throws Exception {
        try (Connection con = DB_Connection.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT status FROM Slot WHERE slot_no IN (SELECT slot_id FROM ParkingEntry)"
             )) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                assertEquals("EMPTY", rs.getString("status"), "Slot should be EMPTY after exit");
            }
        }
    }
}
