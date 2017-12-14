package sh.okx.sql.api.query;

import java.sql.SQLException;

public interface QueryResults {
    /**
     * Move on to the next row in the returned result set
     * @return This object.
     */
    QueryResults next();

    /**
     * Move on to the next row in the returned result set
     * @return Whether this operation succeeded
     * @throws SQLException
     */
    boolean checkNext() throws SQLException;

    /**
     * Get the value at the specified column
     * @param column The column to get the value from
     * @return The value at the specified column
     */
    String getString(String column);

    /**
     * Get the value at the specified column index
     * @param column The column index to get the value from
     * @return The value at the specified column index
     */
    String getString(int column);
}
