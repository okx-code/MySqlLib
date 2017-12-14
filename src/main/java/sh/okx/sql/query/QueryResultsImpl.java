package sh.okx.sql.query;

import sh.okx.sql.api.query.QueryResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class QueryResultsImpl implements QueryResults, Iterable<QueryResults> {
    private ResultSet resultSet;
    private QueryResultsImpl self;

    public QueryResultsImpl(ResultSet resultSet) {
        this.resultSet = resultSet;
        this.self = this;
    }


    @Override
    public QueryResults next() {
        try {
            resultSet.next();
            return this;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkNext() throws SQLException {
        return resultSet.next();
    }

    @Override
    public String getString(String column) {
        try {
            return resultSet.getString(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getString(int column) {
        try {
            return resultSet.getString(column);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Iterator<QueryResults> iterator() {
        return new Iterator<QueryResults>() {
            @Override
            public boolean hasNext() {
                try {
                    boolean yes = resultSet.next();
                    resultSet.previous();
                    return yes;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public QueryResults next() {
                try {
                    resultSet.next();
                    return self;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }
}
