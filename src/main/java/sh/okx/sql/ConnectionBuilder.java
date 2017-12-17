package sh.okx.sql;

import sh.okx.sql.api.Connection;
import sh.okx.sql.api.PooledConnection;
import sh.okx.sql.api.SqlException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    // jdbc:mysql://localhost/
    private String host = "localhost";
    private int port = 3306;

    private boolean useCredentials = false;
    private String user;
    private String password;

    private boolean useDatabase = false;
    private String database;

    public ConnectionBuilder() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ConnectionBuilder setHost(String host) {
        this.host = host;
        return this;
    }

    public ConnectionBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public ConnectionBuilder setCredentials(String user, String password) {
        this.useCredentials = true;
        this.user = user;
        this.password = password;
        return this;
    }

    public ConnectionBuilder setDatabase(String database) {
        if(database == null) {
            this.useDatabase = false;
            return this;
        }
        this.useDatabase = true;
        this.database = database;
        return this;
    }

    public Connection build() {
        String url = "jdbc:mysql://" + host + ":" + port;
        if(useDatabase) {
            url += "/" + database;
        }

        try {
            if (useCredentials) {
                return new ConnectionImpl(DriverManager.getConnection(url, user, password));
            }

            return new ConnectionImpl(DriverManager.getConnection(url));
        } catch(SQLException e) {
            throw new SqlException(e);
        }
    }

    public PooledConnection buildPool() {
        String url = "jdbc:mysql://" + host + ":" + port;
        if(useDatabase) {
            url += "/" + database;
        }

        if (useCredentials) {
            return new PooledConnection(url, user, password);
        }

        return new PooledConnection(url);
    }
}