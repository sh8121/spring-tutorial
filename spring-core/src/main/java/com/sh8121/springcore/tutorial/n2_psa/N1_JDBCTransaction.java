package com.sh8121.springcore.tutorial.n2_psa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class N1_JDBCTransaction {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "SA", "");
        PreparedStatement pstmt = null;
        conn.setAutoCommit(false);
        try {
            pstmt = conn.prepareStatement("insert into MEMBER values(?, ?)");
            pstmt.setString(1, "developer");
            pstmt.setInt(2, 20000);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            conn.close();
        }
    }
}
