package sh.okx.sql.api.clause;

public interface ClauseWhere<E> {
    ClauseWhere<E> equals(Object a, Object b);
    ClauseWhere<E> prepareEquals(Object a, Object b);
    ClauseWhere<E> and();

    E then();
}
