package sh.okx.sql.database;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.database.ExecuteDatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

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
            PreparedStatement statement = connection.getUnderlying().prepareStatement("SHOW DATABASES LIKE ?");
            statement.setString(1, database);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CompletableFuture<Boolean> existsAsync() {
        return CompletableFuture.supplyAsync(this::exists);
    }

    @Override
    public int select() {
        int value = connection.executeUpdate("USE " + database);
        if(value != -1) {
            try {
                connection.getUnderlying().setCatalog(database);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    @Override
    public CompletableFuture<Integer> selectAsync() {
        return CompletableFuture.supplyAsync(this::select);
    }

    @Override
    public int delete() {
        return connection.executeUpdate("DROP DATABASE " + database);
    }

    @Override
    public CompletableFuture<Integer> deleteAsync() {
        return CompletableFuture.supplyAsync(this::delete);
    }

    @Override
    public int create() {
        return connection.executeUpdate("CREATE DATABASE " + database);
    }

    @Override
    public CompletableFuture<Integer> createAsync() {
        return CompletableFuture.supplyAsync(this::create);
    }

    @Override
    public int createIfNotExists() {
        return connection.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
    }

    @Override
    public CompletableFuture<Integer> createIfNotExistsAsync() {
        return CompletableFuture.supplyAsync(this::createIfNotExists);
    }
}
