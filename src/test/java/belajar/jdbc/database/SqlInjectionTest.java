package belajar.jdbc.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {
    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND password = '" + password + "'";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()) {
            // Sukses Login
            System.out.println("Sukses login : " + resultSet.getString("username"));
        } else {
            // Gagal Login
            System.out.println("Gagal login");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
