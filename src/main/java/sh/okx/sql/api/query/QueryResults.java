package sh.okx.sql.api.query;

import java.sql.SQLException;

public interface QueryResults {
    QueryResults next();
    boolean checkNext() throws SQLException;
    String getString(String column);
    String getString(int column);
}
