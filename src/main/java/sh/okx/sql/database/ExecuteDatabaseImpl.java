package sh.okx.sql.database;

import sh.okx.sql.api.database.ExecuteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExecuteDatabaseImpl implements ExecuteDatabase {
    private Connection connection;
    private String database;

    public ExecuteDatabaseImpl(Connection connection, String database) {
        this.connection = connection;
        this.database = database;
    }

    @Override
    public String getName() {
        return database;
    }

    @Override
    public boolean exists() {
        try {
            PreparedStatement statement = connection.prepareStatement("SHOW DATABASES LIKE ?;");
            statement.setString(1, database);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int select() {
        try {
            return connection.createStatement().executeUpdate("USE " + database + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete() {
        try {
            return connection.createStatement().executeUpdate("DROP DATABASE " + database + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int create() {
        try {
            return connection.createStatement().executeUpdate("CREATE DATABASE " + database + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int createIfNotExists() {
        try {
            return connection.createStatement().executeUpdate("CREATE DATABASE IF NOT EXISTS " + database + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
