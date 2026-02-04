 package park.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import park.DB_Connection;

public class CleanupData {
    public void cleanUp() throws Exception {
        Connection con = DB_Connection.getConnection();
        con.setAutoCommit(false);
        try {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM ParkingEntry WHERE Entry_Time < DATE_SUB(NOW(), INTERVAL 45 DAY)");
            int deleted = ps.executeUpdate();
            con.commit();
            System.out.println("Deleted " + deleted + " old parking entries.");
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
}
