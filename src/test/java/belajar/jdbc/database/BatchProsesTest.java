package belajar.jdbc.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProsesTest {
    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES ('arbi@gmail.com', 'hello')";

        for(int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for(int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "arbi@gmail.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}
