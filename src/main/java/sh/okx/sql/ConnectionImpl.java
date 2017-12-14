package sh.okx.sql;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.database.ExecuteDatabase;
import sh.okx.sql.api.query.ExecuteQuery;
import sh.okx.sql.api.update.ExecuteUpdate;
import sh.okx.sql.database.ExecuteDatabaseImpl;
import sh.okx.sql.query.ExecuteQueryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionImpl implements Connection {
    private java.sql.Connection connection;

    public ConnectionImpl(java.sql.Connection connection) {
        this.connection = connection;
    }

    @Override
    public ExecuteDatabase database(String name) {
        return new ExecuteDatabaseImpl(connection, name);
    }

    @Override
    public ExecuteDatabase database() {
        try {
            ResultSet rs = connection.createStatement().executeQuery("SELECT DATABASE() FROM DUAL;");
            rs.next();
            return new ExecuteDatabaseImpl(connection, rs.getString("DATABASE()"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ExecuteQuery query(String table) {
        return new ExecuteQueryImpl(connection, table);
    }

    @Override
    public ExecuteUpdate update(String table) {
        return null;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
