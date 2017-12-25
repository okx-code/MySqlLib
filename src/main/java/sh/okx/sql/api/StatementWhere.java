package sh.okx.sql.api;

import sh.okx.sql.api.clause.ClauseWhere;

public interface StatementWhere<E> {
    /**
     * Prepare an object in the "WHERE" clause to prevent SQL injection.
     * This is automatically called by {@link ClauseWhere#then()}.
     * @param o The object to replace the next ? in a "WHERE" clause.
     * @return This object.
     */
    E prepare(Object o);

    /**
     * Set the "WHERE" clause in the statement as a string. ie: "name = 'Alice'".
     * It is recommended to use the higher level version of this: {@link StatementWhere#where()}
     * @param where What to set the "WHERE" clause as.
     * @return This object.
     */
    E where(String where);

    /**
     * Returns an object which you can modify the "WHERE" clause of the statement from.
     * @return The object which you can modify the "WHERE" clause of the statement from.
     */
    ClauseWhere<E> where();
}
