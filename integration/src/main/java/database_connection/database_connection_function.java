package database_connection;

import scenarios.Context;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database_connection_function {
    public static Statement statement;

    private final Context context;
    public database_connection_function(Context context) {
        this.context = context;
    }

    public ResultSet database_connection(String Query) throws ClassNotFoundException, SQLException {
        String connectionString = "miqa1db3.chzlhtuehsyx.us-west-2.rds.amazonaws.com :" + "5432" + "/" + "ebmiqadb";
        String username = "miqaread";
        String password = "5r6uzvxu4x400tsz03ji";
        Class.forName("org.postgresql.Driver");
        this.context.connection = DriverManager.getConnection(connectionString, username, password);
        statement = this.context.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement.executeQuery(Query);
    }

    public void closeConnectionString() throws SQLException {
        statement.close();
    }

}
