package belajar.jdbc.database;

import com.mysql.cj.protocol.Resultset;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testDatabaseMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet resultSet = databaseMetaData.getTables("belajar_java_database", null, null, null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Table : " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ParameterMetaData parameterMetaData = statement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
        System.out.println(parameterMetaData.getParameterType(1));
        System.out.println(parameterMetaData.getParameterType(2));

        statement.close();
        connection.close();
    }
    /*Parameter MetaData tidak support di DB MySQL*/

    @Test
    void testResultMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Name : " + resultSetMetaData.getColumnName(i));
            System.out.println("Type : " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
