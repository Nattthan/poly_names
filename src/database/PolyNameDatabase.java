package database;

import java.sql.SQLException;

public class PolyNameDatabase extends MySQLDatabase{
    public PolyNameDatabase(String host, int port, String databaseName, String user, String password)
            throws SQLException {
        super(host, port, databaseName, user, password);
    }
}
