package sh.okx.sql.api.clause;

public interface Clause<E> {
    /**
     * Get the statement back from this object.
     * This also modifies the statement! Not calling this can cause errors!
     * @return The original statement.
     */
    E then();

    /**
     * Get this object in SQL language.
     * @return This object, as it would be written as a string in SQL.
     */
    String define();
}
