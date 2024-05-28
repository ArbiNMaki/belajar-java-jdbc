package belajar.jdbc.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDrive = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDrive);
        } catch(SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "malang234malang234";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Sukses connect ke database!");
        } catch(SQLException exception) {
            Assertions.fail(exception);
        }
    }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "malang234malang234";

        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Sukses connect ke database!");
            System.out.println("Menutup koneksi.");
        } catch(SQLException exception) {
            Assertions.fail(exception);
        }
    }
}
