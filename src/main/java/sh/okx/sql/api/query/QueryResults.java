package sh.okx.sql.api.query;

import sh.okx.sql.api.SqlException;

import java.sql.ResultSet;

public interface QueryResults extends Iterable<QueryResults> {
    /**
     * Move on to the next row in the returned result set
     * @return This object.
     */
    QueryResults getNext();

    /**
     * Move on to the next row in the returned result set
     * @return Whether this operation succeeded
     * @throws SqlException If an error occurred.
     */
    boolean next();

    /**
     * Get the underlying ResultSet this object is wrapped around
     * @see ResultSet
     * @return The underlying ResultSet.
     */
    ResultSet getResultSet();

    <E extends Enum> E getEnum(String column, Class<E> theEnum);
}
