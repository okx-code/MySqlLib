package sh.okx.sql.query;

import sh.okx.sql.api.query.ExecuteQuery;
import sh.okx.sql.api.query.StatementSelect;

import java.sql.Connection;

public class ExecuteQueryImpl implements ExecuteQuery {
    private Connection connection;
    private String table;

    public ExecuteQueryImpl(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public StatementSelect select(String... columns) {
        return new StatementSelectImpl(connection, columns, table);
    }

    @Override
    public StatementSelect selectAll() {
        return new StatementSelectImpl(connection, null, table);
    }
}
