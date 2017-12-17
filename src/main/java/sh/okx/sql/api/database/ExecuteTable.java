package sh.okx.sql.api.database;

import sh.okx.sql.api.query.StatementSelect;
import sh.okx.sql.api.update.StatementCreateTable;
import sh.okx.sql.api.update.StatementDropTable;

import java.util.concurrent.CompletableFuture;

public interface ExecuteTable {
    /**
     * Select a list of columns to be returned in the result set.
     * If no arguments are given, this selects all columns.
     * @param columns The columns to be returned in the result set
     * @return An object representing the "SELECT" statement.
     */
    StatementSelect select(String... columns);

    /**
     * Create a table with this name.
     * You cannot create multiple tables at once (this will throw an AssertionError if you try).
     * @return An object used to create a table.
     */
    StatementCreateTable create();

    /**
     * Delete the table(s).
     * Please note that this method is not escaped!
     * @return An object used to delete (drop) tables.
     */
    StatementDropTable delete();

    /**
     * Check whether all of the tables this object represent exist.
     * @return Whether all of the tables this object represent exist.
     */
    boolean exists();

    /**
     * Asynchronous version of {@link ExecuteTable#exists()}
     * @see ExecuteTable#exists()
     */
    CompletableFuture<Boolean> existsAsync();
}
