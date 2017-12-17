package sh.okx.sql.update;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.update.StatementDropTable;

import java.util.concurrent.CompletableFuture;

public class StatementDropTableImpl implements StatementDropTable {
    private boolean temporary = false;
    private boolean ifExists = false;

    private Connection connection;
    private String[] tables;

    public StatementDropTableImpl(Connection connection, String[] tables) {
        this.connection = connection;
        this.tables = tables;
    }

    @Override
    public StatementDropTable temporary() {
        this.temporary = true;
        return this;
    }

    @Override
    public StatementDropTable permanent() {
        this.temporary = false;
        return this;
    }

    @Override
    public StatementDropTable setTemporary(boolean temporary) {
        this.temporary = true;
        return this;
    }

    @Override
    public boolean isTemporary() {
        return temporary;
    }

    @Override
    public StatementDropTable ifExists() {
        this.ifExists = true;
        return this;
    }

    @Override
    public StatementDropTable setIfExists(boolean ifExists) {
        this.ifExists = ifExists;
        return this;
    }

    @Override
    public boolean isIfExists() {
        return ifExists;
    }

    @Override
    public int execute() {
        return connection.executeUpdate("DROP " +
                (temporary ? "TEMPORARY " : "") +
                "TABLE " +
                (ifExists ? "IF EXISTS " : "") +
                String.join(",", tables));
    }

    @Override
    public CompletableFuture<Integer> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }
}
