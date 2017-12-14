package sh.okx.sql.api;

import sh.okx.sql.api.query.ExecuteQuery;
import sh.okx.sql.api.update.ExecuteUpdate;
import sh.okx.sql.api.database.ExecuteDatabase;

import java.sql.SQLException;

public interface Connection {
    ExecuteDatabase database(String name);
    ExecuteDatabase database();

    ExecuteQuery query(String table);
    ExecuteUpdate update(String table);

    void close() throws SQLException;
}
