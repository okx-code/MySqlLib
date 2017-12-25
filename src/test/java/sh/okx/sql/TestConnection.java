package sh.okx.sql;

import org.junit.Test;
import sh.okx.sql.api.Connection;
import sh.okx.sql.api.PooledConnection;
import sh.okx.sql.api.SqlException;
import sh.okx.sql.api.data.NumericDataType;
import sh.okx.sql.api.data.OtherDataType;
import sh.okx.sql.api.database.ExecuteDatabase;
import sh.okx.sql.api.database.ExecuteTable;
import sh.okx.sql.api.query.QueryResults;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;


public class TestConnection {
    @Test
    public void testAsync() {
        Connection connection = new ConnectionBuilder()
                .setCredentials("test", "test")
                .build();

        ExecuteDatabase database = connection.database("test");

        CompletableFuture<Integer> ifNotExistsAsync = database.createIfNotExistsAsync();
        ifNotExistsAsync.thenAccept(i -> database.selectAsync()
                .thenAccept(j -> connection.table("my_table", "t").select().where().prepareEquals("name", "Alice").then().executeAsync()
                        .thenAccept(q -> {
                            try {
                                System.out.println(q.getNext().getResultSet().getString("name"));
                            } catch (SQLException ex) {
                                throw new SqlException(ex);
                            }
                        })));
    }

    @Test
    public void testSync() {
        Connection connection = new ConnectionBuilder()
                .setCredentials("test", "test")
                .setDatabase("test")
                .build();

        QueryResults execute = connection.table("my_table", "b").select().
                execute();


        while(execute.next()) {
            try {
                System.out.println(execute.getResultSet().getString(1));
                System.out.println(execute.getResultSet().getString(2));
            } catch(SQLException ex) {
                throw new SqlException(ex);
            }
        }

    }

    @Test
    public void testPooling() {
        PooledConnection pool = new ConnectionBuilder()
                .setCredentials("test", "test")
                .setDatabase("test")
                .buildPool();

        assert pool.getConnection().database().exists();
    }

    @Test
    public void testTableCreateDelete() {
        Connection connection = new ConnectionBuilder()
                .setCredentials("test", "test")
                .setDatabase("test")
                .build();

        ExecuteTable idTest = connection.table("test_ids");

        idTest.existsAsync().thenAccept(a -> System.out.println("Exists: " + a));
        System.out.println("Delete table: " + idTest.drop().ifExists().execute());
        System.out.println("Create table: " + idTest.create()
                .column().name("id").notNull().primary().key().autoIncrement()
                .type(new NumericDataType(NumericDataType.Type.BIGINT)).then()
                .column().name("name").notNull()
                .type(new OtherDataType(OtherDataType.Type.BLOB)).then().execute());
    }

    @Test
    public void test() {
        Predicate<String> t = String::isEmpty;
        t.test("f");
        System.out.println(t.getClass().getSimpleName());
    }

    @Test
    public void testDelete() throws SQLException {
        Connection connection = new ConnectionBuilder()
                .setCredentials("test", "test")
                .setDatabase("test")
                .build();

        connection.table("test_delete").create().ifNotExists().column("t TEXT").execute();
        connection.table("test_delete").delete().execute();

        System.out.println("Creation length (0): " + connection.table("test_delete").select("COUNT(*)")
                .execute().getNext().getResultSet().getString("COUNT(*)"));

        connection.executeUpdate("INSERT INTO test_delete (t) VALUES ('hello')");
        connection.executeUpdate("INSERT INTO test_delete (t) VALUES ('hi')");

        System.out.println("Insertion length (2): " + connection.table("test_delete").select("COUNT(*)")
                .execute().getNext().getResultSet().getString("COUNT(*)"));

        connection.table("test_delete").delete().where().prepareEquals("t", "hi").then().execute();

        System.out.println("Deletion length (1): " + connection.table("test_delete").select("COUNT(*)")
                .execute().getNext().getResultSet().getString("COUNT(*)"));
    }
}
