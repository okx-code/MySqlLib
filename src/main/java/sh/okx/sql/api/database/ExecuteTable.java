package sh.okx.sql.api.database;

import sh.okx.sql.api.query.StatementSelect;

public interface ExecuteTable {
    /**
     * Select a list of columns to be returned in the result set.
     * If no arguments are given, this selects all columns.
     * @param columns The columns to be returned in the result set
     * @return An object representing the "SELECT" statement.
     */
    StatementSelect select(String... columns);
}
