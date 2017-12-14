package sh.okx.sql.api.query;

public interface ExecuteQuery {
    /**
     * Select a list of columns to be returned in the result set
     * @param columns The columns to be returned in the result set
     * @return An object representing the "SELECT" statement.
     */
    StatementSelect select(String... columns);

    /**
     * Select all columsn to be returned in the result set
     * @return An object representing the "SELECT" statement.
     */
    StatementSelect selectAll();
}

