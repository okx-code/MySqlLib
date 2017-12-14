package sh.okx.sql.api.query;

import sh.okx.sql.api.clause.ClauseWhere;

import java.sql.SQLException;

public interface StatementSelect {
    StatementSelect where(String where);
    ClauseWhere<StatementSelect> where();

    StatementSelect prepare(Object o);

    QueryResults execute() throws SQLException;
}
