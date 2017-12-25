package sh.okx.sql.clause;

import sh.okx.sql.api.StatementWhere;
import sh.okx.sql.api.clause.ClauseWhere;

public class ClauseWhereImpl<E extends StatementWhere> implements ClauseWhere<E> {
    private E statement;
    private StringBuilder where = new StringBuilder();

    public ClauseWhereImpl(E statement) {
        this.statement = statement;
    }

    @Override
    public ClauseWhere<E> equals(Object a, Object b) {
        where.append(String.valueOf(a)).append("=").append(String.valueOf(b));
        return this;
    }

    @Override
    public ClauseWhere<E> prepareEquals(Object a, Object b) {
        where.append(String.valueOf(a)).append("=").append("?");
        statement.prepare(b);
        return this;
    }

    @Override
    public ClauseWhere<E> and() {
        where.append(" AND ");
        return this;
    }

    @Override
    public ClauseWhere<E> or() {
        where.append(" OR ");
        return this;
    }

    @Override
    public E then() {
        statement.where(define());
        return statement;
    }

    @Override
    public String define() {
        return where.toString();
    }
}
