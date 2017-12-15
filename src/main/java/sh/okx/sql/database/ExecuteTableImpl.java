package sh.okx.sql.database;

import sh.okx.sql.api.database.ExecuteTable;
import sh.okx.sql.api.query.StatementSelect;
import sh.okx.sql.query.StatementSelectImpl;

import java.sql.Connection;

public class ExecuteTableImpl implements ExecuteTable {
    private Connection connection;
    private String table;

    public ExecuteTableImpl(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public StatementSelect select(String... columns) {
        return new StatementSelectImpl(connection, columns, table);
    }
}
