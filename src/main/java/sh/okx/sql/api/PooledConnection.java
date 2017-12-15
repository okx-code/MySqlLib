package sh.okx.sql.api;

public interface PooledConnection {
    Connection getConnection();
}
