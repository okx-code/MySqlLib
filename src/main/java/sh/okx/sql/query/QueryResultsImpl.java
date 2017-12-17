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
    public QueryResults getNext() {
        try {
            resultSet.next();
            return this;
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }

    @Override
    public boolean next() {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new SqlException(e);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public <E extends Enum> E getEnum(String column, Class<E> theEnum) {
        E[] constants = theEnum.getEnumConstants();
        for(E constant : constants) {
            if(constant.name().equalsIgnoreCase(column)) {
                return constant;
            }
        }
        return null;
    }
    
    public Iterator<QueryResults> iterator() {
        return new Iterator<QueryResults>() {
            
            public boolean hasNext() {
                try {
                    boolean yes = resultSet.next();
                    resultSet.previous();
                    return yes;
                } catch (SQLException e) {
                    throw new SqlException(e);
                }
            }
            
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
