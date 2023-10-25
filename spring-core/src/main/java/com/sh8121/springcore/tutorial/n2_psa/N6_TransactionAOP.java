package com.sh8121.springcore.tutorial.n2_psa;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

public class N6_TransactionAOP {

    public static void main(String[] args) throws SQLException {
        var appContext = new AnnotationConfigApplicationContext(Config.class);
        var service = appContext.getBean(Service.class);
        service.process();
    }

    @RequiredArgsConstructor
    static class Service {

        private final DataSource dataSource;

        @Transactional
        public void process() throws SQLException {
            Connection conn = null;
            PreparedStatement psmt = null;
            try {
                conn = DataSourceUtils.getConnection(dataSource);
                psmt = conn.prepareStatement("insert into MEMBER values(?,?)");
                psmt.setString(1, "developer");
                psmt.setInt(2, 10000);
                psmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                if (psmt != null) {
                    psmt.close();
                }
                if (conn != null) {
                    DataSourceUtils.releaseConnection(conn, dataSource);
                }
            }
        }
    }


    @Configuration
    @EnableTransactionManagement
    static class Config {

        //TODO Transaction AOP 자세히 다룰 때 정리
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
            return new Service(dataSource());
        }
    }
}
