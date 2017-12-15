package sh.okx.sql.query;

import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.clause.ClauseWhere;
import sh.okx.sql.api.query.QueryResults;
import sh.okx.sql.api.query.StatementSelect;
import sh.okx.sql.clause.ClauseWhereImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StatementSelectImpl implements StatementSelect {
    private Connection connection;
    private String[] columns;
    private String table;
    private String where = null;
    private List<String> prepared = new ArrayList<>();

    public StatementSelectImpl(Connection connection, String[] columns, String table) {
        this.connection = connection;
        this.columns = columns;
        this.table = table;
    }

    @Override
    public StatementSelect where(String where) {
        this.where = where;
        return this;
    }

    @Override
    public ClauseWhere<StatementSelect> where() {
        return new ClauseWhereImpl<>(this);
    }

    @Override
    public StatementSelect prepare(Object o) {
        prepared.add(String.valueOf(o));
        return this;
    }

    @Override
    public QueryResults execute() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT " +
                    (columns == null || columns.length == 0 ? "*" : String.join(",", columns)) +
                    " FROM " + table + (where == null ? "" : " WHERE " + where + "") + ";");

            for(int i = 0; i < prepared.size(); i++) {
                statement.setObject(i+1, prepared.get(i));
            }

            return new QueryResultsImpl(statement.executeQuery());
        } catch (SQLException e) {
            throw new SqlException(e);
        }

    }

    @Override
    public CompletableFuture<QueryResults> executeAsync() {
        return CompletableFuture.supplyAsync(this::execute);
    }
}
