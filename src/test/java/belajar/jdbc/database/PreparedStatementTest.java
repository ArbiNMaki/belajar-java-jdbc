package belajar.jdbc.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {
    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "admin";
        String password = "admin";

        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet result = preparedStatement.executeQuery();
        if(result.next()) {
            System.out.println("Sukses login : " + result.getString("username"));
        } else {
            System.out.println("Gagal login");
        }

        preparedStatement.close();
        connection.close();
    }
}
