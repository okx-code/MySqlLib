package sh.okx.sql.api.update;

public interface StatementInsertInto extends StatementUpdate {
    StatementInsertInto value(String name, Object value);
}
