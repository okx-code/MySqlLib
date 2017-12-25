package sh.okx.sql.update;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.clause.ClauseWhere;
import sh.okx.sql.api.update.StatementDeleteFrom;
import sh.okx.sql.clause.ClauseWhereImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StatementDeleteFromImpl implements StatementDeleteFrom {
    private Connection connection;
    private String[] tables;

    private List<String> prepared = new ArrayList<>();
    private String where = null;

    private String join = "JOIN";
    private boolean lowPriority = false, quick = false, ignore = false;

    public StatementDeleteFromImpl(Connection connection, String[] tables) {
        this.connection = connection;
        this.tables = tables;
    }

    @Override
    public StatementDeleteFrom lowPriority() {
        this.lowPriority = true;
        return this;
    }

    @Override
    public StatementDeleteFrom quick() {
        this.quick = true;
        return this;
    }

    @Override
    public StatementDeleteFrom ignore() {
        this.ignore = true;
        return this;
    }

    @Override
    public StatementDeleteFrom joinTables(String join) {
        this.join = join;
        return this;
    }

    @Override
    public int execute() {
        try {
            PreparedStatement statement = connection.getUnderlying().prepareStatement(toString());

            for (int i = 0; i < prepared.size(); i++) {
                statement.setObject(i + 1, prepared.get(i));
            }

            return statement.executeUpdate();
        } catch(SQLException ex) {
            throw new SqlException(ex);
        }
    }

    @Override
    public String toString() {
        return "DELETE " +
                (lowPriority ? "LOW_PRIORITY " : "") +
                (quick ? "QUICK " : "") +
                (ignore ? "IGNORE " : "") +
                String.join(", ", tables) +
                " FROM " + String.join(" " + join + " ", tables) +
                (where != null ? " WHERE " + where : "");
    }

    @Override
    public CompletableFuture<Integer> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }

    @Override
    public StatementDeleteFrom prepare(Object o) {
        prepared.add(String.valueOf(o));
        return this;
    }

    @Override
    public StatementDeleteFrom where(String where) {
        this.where = where;
        return this;
    }

    @Override
    public ClauseWhere<StatementDeleteFrom> where() {
        return new ClauseWhereImpl<>(this);
    }
}
