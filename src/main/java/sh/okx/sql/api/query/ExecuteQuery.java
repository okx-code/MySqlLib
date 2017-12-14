package sh.okx.sql.api.query;

public interface ExecuteQuery {
    StatementSelect select(String... columns);
    StatementSelect selectAll();
}

