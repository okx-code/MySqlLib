package sh.okx.sql;

import org.junit.Test;

import java.sql.*;

public class TestJDBC {
    @Test
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
        resultSet.next();

        System.out.println(resultSet.getString("name"));
    }
}
