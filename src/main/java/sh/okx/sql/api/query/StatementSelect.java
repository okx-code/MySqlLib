package sh.okx.sql.api.query;

import sh.okx.sql.api.StatementWhere;

import java.util.concurrent.CompletableFuture;

public interface StatementSelect extends StatementQuery, StatementWhere<StatementSelect> {
    /**
     * A string to join the table names by in the select statement.
     * @param join What to join the tables with. Default is "JOIN".
     * @return This object.
     */
    StatementSelect joinTables(String join);

    /**
     * Execute the statement asynchronously.
     * @see StatementSelect#execute()
     */
    CompletableFuture<QueryResults> executeAsync();
}
