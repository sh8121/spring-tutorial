package com.sh8121.springtutorial.springcore.tutorial.n2_psa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

public class N5_PlatformTransactionManagerWithDI {

    public static void main(String[] args) throws SQLException {
        var appContext = new AnnotationConfigApplicationContext(Config.class);
        var service = appContext.getBean(Service.class);
        service.processData();
    }

    @RequiredArgsConstructor
    static class Service {

        private final DataSource dataSource;
        private final PlatformTransactionManager transactionManager;

        public void processData() throws SQLException {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
            Connection conn = null;
            PreparedStatement psmt = null;
            try {
                conn = DataSourceUtils.getConnection(dataSource);
                psmt = conn.prepareStatement("insert into MEMBER values(?, ?)");
                psmt.setString(1, "developer");
                psmt.setInt(2, 10000);
                psmt.executeUpdate();
                psmt.close();
                DataSourceUtils.releaseConnection(conn, dataSource);
                transactionManager.commit(status);
            } catch (Exception ex) {
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    DataSourceUtils.releaseConnection(conn, dataSource);
                }
                transactionManager.rollback(status);
            }
        }
    }

    @Configuration
    static class Config {

        @Bean
        public DataSource dataSource() {
            return new DriverManagerDataSource("jdbc:h2:tcp://localhost/~/test", "SA", "");
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        public Service service() {
            return new Service(dataSource(), transactionManager());
        }
    }
}
