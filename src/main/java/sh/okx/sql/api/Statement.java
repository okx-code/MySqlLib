package sh.okx.sql.api;

import sh.okx.sql.api.query.QueryResults;

public interface Statement {
    /**
     * Execute the statement.
     * @return The results of the statement.
     * @throws SqlException If a {@link java.sql.SQLException} occurs.
     */
    QueryResults execute();
}
