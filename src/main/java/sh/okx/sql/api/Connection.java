package sh.okx.sql.api;

import sh.okx.sql.api.database.ExecuteDatabase;
import sh.okx.sql.api.database.ExecuteTable;

public interface Connection extends AutoCloseable {
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
     * Get an object to manipulate the specified table(s), such as performing statements on it.
     * @param tables The name(s) of the table(s)
     * @return The object for manipulating the table(s).
     */
    ExecuteTable table(String... tables);

    /**
     * Update the database with a statement synchronously.
     * You may provide arguments used to prepare the statement.
     * If no extra arguments are provided, this statement will not be prepared.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int executeUpdate(String statement, String... prepared);

    /**
     * Get the underlying {@link java.sql.Connection} within this object.
     * @return The underlying connection.
     */
    java.sql.Connection getUnderlying();
}
