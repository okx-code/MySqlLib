package sh.okx.sql.clause;

import sh.okx.sql.api.clause.ClauseWhere;
import sh.okx.sql.api.query.StatementSelect;

public class ClauseWhereImpl<E extends StatementSelect> implements ClauseWhere<E> {
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
    public E then() {
        statement.where(where.toString());
        return statement;
    }
}
