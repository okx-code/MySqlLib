package sh.okx.sql.api;

import java.sql.SQLException;

public class SqlException extends RuntimeException {
    private SQLException underlying;

    public SqlException(SQLException underlying) {
        this.underlying = underlying;
    }

    public SQLException getUnderlying() {
        return underlying;
    }

    @Override
    public String getMessage() {
        return underlying.getMessage();
    }
}
