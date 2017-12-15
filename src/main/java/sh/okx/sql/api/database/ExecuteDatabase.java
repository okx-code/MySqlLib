package sh.okx.sql.api.database;

import java.util.concurrent.CompletableFuture;

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
     * Asynchronous version of {@link ExecuteDatabase#exists()}.
     * @see ExecuteDatabase#exists()
     */
    CompletableFuture<Boolean> existsAsync();

    /**
     * Select this database to perform statements on.
     * Please note that this method is not escaped!
     * @return The returned row count or <code>-1</code> on failure.
     */
    int select();

    /**
     * Asynchronous version of {@link ExecuteDatabase#exists()}.
     * @see ExecuteDatabase#exists()
     */
    CompletableFuture<Integer> selectAsync();

    /**
     * Delete this database.
     * Please note that this method is not escaped!
     * @return The returned row count or <code>-1</code> on failure.
     */
    int delete();

    /**
     * Asynchronous version of {@link ExecuteDatabase#delete()}.
     * @see ExecuteDatabase#deleteAsync()
     */
    CompletableFuture<Integer> deleteAsync();

    /**
     * Create this database.
     * Please note that this method is not escaped!
     * @return The returned row count or <code>-1</code> on failure.
     */
    int create();

    /**
     * Asynchronous version of {@link ExecuteDatabase#createAsync()}.
     * @see ExecuteDatabase#createAsync()
     */
    CompletableFuture<Integer> createAsync();

    /**
     * Create this database if it does not exist.
     * Please note that this method is not escaped!
     * @return The returned row count or <code>-1</code> on failure.
     */
    int createIfNotExists();

    /**
     * Asynchronous version of {@link ExecuteDatabase#createIfNotExists()}.
     * @see ExecuteDatabase#createIfNotExists()
     */
    CompletableFuture<Integer> createIfNotExistsAsync();
}
