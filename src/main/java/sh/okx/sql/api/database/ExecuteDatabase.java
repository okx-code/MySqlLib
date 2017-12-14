package sh.okx.sql.api.database;

public interface ExecuteDatabase {
    String getName();

    boolean exists();

    int select();
    int delete();
    int create();
    int createIfNotExists();
}
