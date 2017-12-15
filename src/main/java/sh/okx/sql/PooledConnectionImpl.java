package sh.okx.sql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import sh.okx.sql.api.Connection;
import sh.okx.sql.api.PooledConnection;
import sh.okx.sql.api.SqlException;

import java.sql.SQLException;

public class PooledConnectionImpl implements PooledConnection {
    private MysqlDataSource dataSource = new MysqlDataSource();

    public PooledConnectionImpl(String url) {
        dataSource.setUrl(url);
    }

    public PooledConnectionImpl(String url, String user, String password) {
        this(url);

        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    @Override
    public Connection getConnection() {
        try {
            return new ConnectionImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }
}
