package sh.okx.sql.api.query;

import sh.okx.sql.api.SqlException;

public interface StatementQuery {
    /**
     * Execute the statement.
     * @return The results of the statement.
     * @throws SqlException If a {@link java.sql.SQLException} occurs.
     */
    QueryResults execute();

    /**
     * Get the query this statement will run in
     * @return
     */
    String toString();
}
