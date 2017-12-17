package sh.okx.sql.query;

import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.query.QueryResults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class QueryResultsImpl implements QueryResults {
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
    public boolean checkNext() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }

    @Override
    public String getString(String column) {
        try {
            assert !resultSet.isBeforeFirst();

            return resultSet.getString(column);
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }

    @Override
    public String getString(int column) {
        try {
            assert !resultSet.isBeforeFirst();

            return resultSet.getString(column);
        } catch (SQLException e) {
            throw new SqlException(e);
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
                    throw new SqlException(e);
                }
            }

            @Override
            public QueryResults next() {
                try {
                    resultSet.next();
                    return self;
                } catch (SQLException e) {
                    throw new SqlException(e);
                }
            }
        };
    }
}
