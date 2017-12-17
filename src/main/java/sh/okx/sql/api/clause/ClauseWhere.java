package sh.okx.sql.api.clause;

public interface ClauseWhere<E> extends Clause<E> {
    /**
     * Add an equals operation to the "WHERE" clause.
     * Calling this method with 'id' and '6' represents 'id=6' in the "WHERE" clause
     * @param a The column name for the equals operation.
     * @param b The value.
     * @return This object.
     */
    ClauseWhere<E> equals(Object a, Object b);

    /**
     * Add a prepared equals operation to the "WHERE" clause.
     * This prevents SQL injection on the object 'b'.
     * @param a The column name for the equals operation.
     * @param b The value.
     * @return This object.
     */
    ClauseWhere<E> prepareEquals(Object a, Object b);

    /**
     * Add an "AND" operation to the "WHERE" clause.
     * This requires both of the operations on its left and right side to be true.
     * @return This object.
     */
    ClauseWhere<E> and();

    /**
     * Add an "OR" operation to the "WHERE" clause.
     * This requires at least one operation on either side of it to be true.
     * @return This object.
     */
    ClauseWhere<E> or();
}
