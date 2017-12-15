package sh.okx.sql;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.database.ExecuteDatabase;
import sh.okx.sql.api.database.ExecuteTable;
import sh.okx.sql.database.ExecuteDatabaseImpl;
import sh.okx.sql.database.ExecuteTableImpl;

import java.sql.SQLException;

public class ConnectionImpl implements Connection {
    private java.sql.Connection connection;

    public ConnectionImpl(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public ExecuteDatabase database(String name) {
        return new ExecuteDatabaseImpl(this, name);
    }

    @Override
    public ExecuteDatabase database() {
        String catalog = null;
        try {
            catalog = connection.getCatalog();
        } catch (SQLException ignored) {}

        if(catalog == null) {
            return null;
        }

        return new ExecuteDatabaseImpl(this, catalog);
    }

    @Override
    public ExecuteTable table(String table) {
        return new ExecuteTableImpl(connection, table);
    }

    @Override
    public int executeUpdate(String statement) {
        try {
            return connection.createStatement().executeUpdate(statement);
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }

    @Override
    public java.sql.Connection getUnderlying() {
        return connection;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }
}
