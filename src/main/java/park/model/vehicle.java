package park.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import park.DB_Connection;

public class vehicle {
    private int id;
    private String vehicle_type;
    private String license_plate;
    private String vehicle_owner_name;
    private String vehicle_registration_id;
    private String driver_name;
    private String driver_license_id;
    private String vehicle_brand;

    public vehicle(String vehicle_type, String license_plate, String vehicle_owner_name,
                   String vehicle_registration_id, String driver_name, String driver_license_id, String vehicle_brand)
            throws Exception {
        Connection con = DB_Connection.getConnection();
        con.setAutoCommit(false);
        try {
            // Corrected: use the parameter vehicle_registration_id instead of uninitialized field
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, vehicle_type, license_plate, vehicle_owner_name, " +
                            "vehicle_registration_id, driver_name, driver_license_id, vehicle_brand " +
                            "FROM vehicle WHERE vehicle_registration_id=? FOR UPDATE");

            ps.setString(1, vehicle_registration_id); // use constructor argument
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.vehicle_type = rs.getString("vehicle_type");
                this.license_plate = rs.getString("license_plate");
                this.vehicle_owner_name = rs.getString("vehicle_owner_name");
                this.vehicle_registration_id = rs.getString("vehicle_registration_id");
                this.driver_name = rs.getString("driver_name");
                this.driver_license_id = rs.getString("driver_license_id");
                this.vehicle_brand = rs.getString("vehicle_brand");
            } else {
                PreparedStatement insert = con.prepareStatement(
                        "INSERT INTO vehicle(vehicle_type, license_plate, vehicle_owner_name, " +
                                "vehicle_registration_id, driver_name, driver_license_id, vehicle_brand) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                insert.setString(1, vehicle_type);
                insert.setString(2, license_plate);
                insert.setString(3, vehicle_owner_name);
                insert.setString(4, vehicle_registration_id);
                insert.setString(5, driver_name);
                insert.setString(6, driver_license_id);
                insert.setString(7, vehicle_brand);
                insert.executeUpdate();

                ResultSet keyRs = insert.getGeneratedKeys();
                keyRs.next();
                this.id = keyRs.getInt(1);

                this.vehicle_type = vehicle_type;
                this.license_plate = license_plate;
                this.vehicle_owner_name = vehicle_owner_name;
                this.vehicle_registration_id = vehicle_registration_id;
                this.driver_name = driver_name;
                this.driver_license_id = driver_license_id;
                this.vehicle_brand = vehicle_brand;
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

    // Getters
    public int getId() { return id; }
    public String getVehicle_type() { return vehicle_type; }
    public String getLicense_plate() { return license_plate; }
    public String getVehicle_owner_name() { return vehicle_owner_name; }
    public String getVehicle_registration_id() { return vehicle_registration_id; }
    public String getDriver_name() { return driver_name; }
    public String getDriver_license_id() { return driver_license_id; }
    public String getVehicle_brand() { return vehicle_brand; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setVehicle_type(String vehicle_type) { this.vehicle_type = vehicle_type; }
    public void setLicense_plate(String license_plate) { this.license_plate = license_plate; }
    public void setVehicle_owner_name(String vehicle_owner_name) { this.vehicle_owner_name = vehicle_owner_name; }
    public void setVehicle_registration_id(String vehicle_registration_id) { this.vehicle_registration_id = vehicle_registration_id; }
    public void setDriver_name(String driver_name) { this.driver_name = driver_name; }
    public void setDriver_license_id(String driver_license_id) { this.driver_license_id = driver_license_id; }
    public void setVehicle_brand(String vehicle_brand) { this.vehicle_brand = vehicle_brand; }
}
