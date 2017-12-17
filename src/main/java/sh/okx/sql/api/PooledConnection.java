package sh.okx.sql.api;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import sh.okx.sql.ConnectionImpl;

import java.sql.SQLException;

public class PooledConnection {
    private MysqlDataSource dataSource = new MysqlDataSource();

    public PooledConnection(String url) {
        dataSource.setUrl(url);
    }

    public PooledConnection(String url, String user, String password) {
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    public void setDatabase(String database) {
        dataSource.setDatabaseName(database);
    }

    /**
     * Get a connection object from the pool.
     * @return A connection object from the pool.
     */
    public Connection getConnection() {
        try {
            return new ConnectionImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }
}
