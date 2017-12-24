package sh.okx.sql.api.query;

import sh.okx.sql.api.clause.ClauseWhere;

import java.util.concurrent.CompletableFuture;

public interface StatementSelect extends StatementQuery {
    /**
     * A string to join the table names by in the select statement.
     * @param join What to join the tables with. Default is "JOIN".
     * @return This object.
     */
    StatementSelect joinTables(String join);

    /**
     * Set the "WHERE" clause in the statement as a string. ie: "name = 'Alice'".
     * It is recommended to use the higher level version of this: {@link StatementSelect#where()}
     * @param where What to set the "WHERE" clause as.
     * @return This object.
     */
    StatementSelect where(String where);

    /**
     * Returns an object which you can modify the "WHERE" clause of the statement from.
     * @return The object which you can modify the "WHERE" clause of the statement from.
     */
    ClauseWhere<StatementSelect> where();

    /**
     * Prepare an object in the "WHERE" clause to prevent SQL injection.
     * This is automatically called by {@link ClauseWhere#then()}.
     * @param o The object to replace the next ? in a "WHERE" clause.
     * @return This object.
     */
    StatementSelect prepare(Object o);

    /**
     * Execute the statement asynchronously.
     * @see StatementSelect#execute()
     */
    CompletableFuture<QueryResults> executeAsync();
}
