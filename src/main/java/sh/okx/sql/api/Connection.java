package sh.okx.sql.api;

import sh.okx.sql.api.query.ExecuteQuery;
import sh.okx.sql.api.update.ExecuteUpdate;
import sh.okx.sql.api.database.ExecuteDatabase;

import java.sql.SQLException;

public interface Connection {
    /**
     * Get a database with the specified name.
     * This will never return null, and the database is not required to exist.
     * @param name The name of the database
     * @return An object on which to perform operations about the database
     */
    ExecuteDatabase database(String name);

    /**
     * Get the currently selected database.
     * If no database is selected, this will error and return null.
     * @return The currently selected database.
     */
    ExecuteDatabase database();

    /**
     * Perform query statements on the specified table
     * @param table The name of the table
     * @return The object about which to perform query statements on the specified table
     */
    ExecuteQuery query(String table);

    /**
     * Perform update statements on the specified table
     * @param table The name of the table
     * @return The object about which to perform update statements on the specified table
     */
    ExecuteUpdate update(String table);

    /**
     * Close the current connection to the MySQL database
     * @throws SQLException
     */
    void close() throws SQLException;
}
