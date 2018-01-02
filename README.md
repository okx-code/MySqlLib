This is a MySQL library I made to abstract direct statement queries.

You should rarely need to run a statement like `SELECT * FROM users WHERE user=?`. This is done via builder methods.

To establish a simple connection to the MySQL server and setup a database:

    Connection connection = new ConnectionBuilder()
        .setCredentials("root", "123");
    ExecuteDatabase database = connection.database("website");
    database.createIfNotExistsAsync()
        .thenAccept(i -> database.selectAsync());
        
If you want to connect to an already existing database, use `ConnectionBuilder#setDatabase(String)`

To run a simple statement equal to `SELECT * FROM users WHERE user=?`:

    connection.table("users")
        .select()
        .where().prepareEquals("user", user).then()
        .executeAsync();
        
Most statements support multiple tables, but the JavaDocs will tell you if they don't.