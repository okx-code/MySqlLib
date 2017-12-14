package sh.okx.sql.api.database;

public interface ExecuteDatabase {
    /**
     * Get the name of this database.
     * @return The name of this database.
     */
    String getName();

    /**
     * Check whether a database with this name exists.
     * @return Whether a database with this name exists.
     */
    boolean exists();

    /**
     * Select this database to perform statements on.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int select();

    /**
     * Delete this database.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int delete();

    /**
     * Create this database.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int create();

    /**
     * Create this database if it does not exist.
     * @return The returned row count or <code>-1</code> on failure.
     */
    int createIfNotExists();
}
