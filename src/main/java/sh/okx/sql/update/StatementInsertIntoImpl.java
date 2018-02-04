package sh.okx.sql.update;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.update.StatementInsertInto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class StatementInsertIntoImpl implements StatementInsertInto {
    private Map<String, Object> values = new HashMap<>();

    private Connection connection;
    private String table;

    public StatementInsertIntoImpl(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public StatementInsertInto value(String name, Object value) {
        values.put(name, value);
        return this;
    }

    @Override
    public int execute() {
        try {
            PreparedStatement statement = connection.getUnderlying().prepareStatement(toString());

            int i = 0;
            for(Map.Entry<String, Object> entry : values.entrySet()) {
                i++;
                statement.setObject(i, entry.getValue());
            }

            return statement.executeUpdate();
        } catch(SQLException ex) {
            throw new SqlException(ex);
        }
    }

    @Override
    public CompletableFuture<Integer> executeAsync() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder qms = new StringBuilder();
        for(int i = 0; i < values.size(); i++ ){
            if(i > 0) {
                qms.append(", ");
            }

            qms.append("?");
        }


        return "INSERT INTO " + table + "(" + String.join(", ", values.keySet()) + ") VALUES (" + qms + ")";
    }
}
