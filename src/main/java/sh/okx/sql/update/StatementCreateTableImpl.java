package sh.okx.sql.update;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.clause.ClauseColumn;
import sh.okx.sql.api.update.StatementCreateTable;
import sh.okx.sql.clause.ClauseColumnImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class StatementCreateTableImpl implements StatementCreateTable {
    private boolean temporary = false;
    private boolean ifNotExists = false;
    private String like = null;
    private List<String> columns = new ArrayList<>();

    private Connection connection;
    private String table;

    public StatementCreateTableImpl(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public StatementCreateTable temporary() {
        this.temporary = true;
        return this;
    }

    @Override
    public StatementCreateTable permanent() {
        this.temporary = false;
        return this;
    }

    @Override
    public StatementCreateTable setTemporary(boolean temporary) {
        this.temporary = temporary;
        return this;
    }

    @Override
    public boolean isTemporary() {
        return temporary;
    }

    @Override
    public StatementCreateTable ifNotExists() {
        this.ifNotExists = true;
        return this;
    }

    @Override
    public StatementCreateTable setIfNotExists(boolean ifNotExists) {
        this.ifNotExists = ifNotExists;
        return this;
    }

    @Override
    public boolean isIfNotExists() {
        return ifNotExists;
    }

    @Override
    public StatementCreateTable like(String table) {
        this.like = table;
        return this;
    }

    @Override
    public String getLike() {
        return like;
    }

    @Override
    public ClauseColumn<StatementCreateTable> column() {
        return new ClauseColumnImpl<>(this);
    }

    @Override
    public StatementCreateTable column(String column) {
        columns.add(column);
        return this;
    }

    @Override
    public int execute() {
        StringBuilder sb = new StringBuilder("CREATE ");
        if (temporary) {
            sb.append("TEMPORARY ");
        }
        sb.append("TABLE ");
        if (ifNotExists) {
            sb.append("IF NOT EXISTS ");
        }
        sb.append(table);

        if (like != null) {
            return connection.executeUpdate(sb.append(" LIKE ").append(like).toString());
        }

        if(columns.size() > 0) {
            sb.append("(").append(String.join(", ", columns)).append(")");
        }
        return connection.executeUpdate(sb.toString());
    }
    @Override
    public CompletableFuture<Integer> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }
}
