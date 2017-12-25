package sh.okx.sql.database;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.database.ExecuteTable;
import sh.okx.sql.api.query.StatementSelect;
import sh.okx.sql.api.update.StatementCreateTable;
import sh.okx.sql.api.update.StatementDeleteFrom;
import sh.okx.sql.api.update.StatementDropTable;
import sh.okx.sql.query.StatementSelectImpl;
import sh.okx.sql.update.StatementCreateTableImpl;
import sh.okx.sql.update.StatementDeleteFromImpl;
import sh.okx.sql.update.StatementDropTableImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;


public class ExecuteTableImpl implements ExecuteTable {
    private Connection connection;
    private String[] tables;

    public ExecuteTableImpl(Connection connection, String[] tables) {
        this.connection = connection;
        this.tables = tables;
    }

    @Override
    public StatementSelect select(String... columns) {
        return new StatementSelectImpl(connection.getUnderlying(), columns, tables);
    }

    @Override
    public StatementCreateTable create() {
        assert tables.length == 1;
        return new StatementCreateTableImpl(connection, tables[0]);
    }

    @Override
    public StatementDropTable drop() {
        return new StatementDropTableImpl(connection, tables);
    }

    @Override
    public StatementDeleteFrom delete() {
        return new StatementDeleteFromImpl(connection, tables);
    }

    @Override
    public boolean exists() {
        boolean allExists = true;
        for(String table : tables) {
            try {
                PreparedStatement statement = connection.getUnderlying().prepareStatement("SHOW TABLES LIKE ?");
                statement.setString(1, table);
                allExists = statement.executeQuery().next();
                if (!allExists) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return allExists;
    }

    @Override
    public CompletableFuture<Boolean> existsAsync() {
        return CompletableFuture.supplyAsync(this::exists);
    }
}
