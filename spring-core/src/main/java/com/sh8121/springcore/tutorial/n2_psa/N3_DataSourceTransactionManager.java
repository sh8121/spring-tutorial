package com.sh8121.springcore.tutorial.n2_psa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class N3_DataSourceTransactionManager {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:h2:tcp://localhost/~/test", "SA", "");
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        PreparedStatement psmt = null;
        try {
            ConnectionHolder connectionHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(dataSource);
            Connection conn = connectionHolder.getConnection();
            psmt = conn.prepareStatement("insert into MEMBER values(?,?)");
            psmt.setString(1, "developer");
            psmt.setInt(2, 5000);
            psmt.executeUpdate();
            psmt.close();
            transactionManager.commit(status);
        } catch (Exception ex) {
            if (psmt != null) {
                psmt.close();
            }
            transactionManager.rollback(status);
        }
    }
}
