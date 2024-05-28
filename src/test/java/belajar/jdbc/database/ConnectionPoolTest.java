package belajar.jdbc.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    @Test
    void testHikariCp() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_database");
        config.setUsername("root");
        config.setPassword("malang234malang234");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try {
            HikariDataSource dataSource = new HikariDataSource(config);

            Connection connection = dataSource.getConnection();
            System.out.println("Sukses mengambil koneksi");

            connection.close();
            System.out.println("Sukses mengembalikan koneksi");

            dataSource.close();
            System.out.println("Sukses menutup pool");
        } catch(SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testUtil() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
