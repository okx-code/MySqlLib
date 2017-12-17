package sh.okx.sql.api;

import java.sql.SQLException;

/**
 * Similar to an SQLException, but this does not need to be caught,
 * so this is useful if you are sure what you are running won't cause an error,
 * and you don't want to use try-catch.
 */
public class SqlException extends RuntimeException {
    private SQLException underlying;

    public SqlException(SQLException underlying) {
        this.underlying = underlying;
    }

    /**
     * Returns the SQLException that is wrapped around by this object.
     * @return The underlying SQLException.
     * @see SQLException
     */
    public SQLException getUnderlying() {
        return underlying;
    }

    @Override
    public String getMessage() {
        return underlying.getMessage();
    }
}
