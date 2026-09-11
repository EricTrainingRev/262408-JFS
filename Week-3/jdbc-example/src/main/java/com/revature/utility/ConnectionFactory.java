package com.revature.utility;

import com.revature.exceptions.CreateConnectionFail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    private static final String url = System.getenv("DATABASE-PATH");
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection getAutoCommitConnect(){
        try{
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch(SQLException exception){
            exception.printStackTrace();
            throw new CreateConnectionFail("Failed to create exception");
        }

    }

    public static Connection getManualCommitConnection(){
        try{
            Connection connection = DriverManager.getConnection(POSTGRES_URL, "postgres", "password");
            // when using sqlite make sure to also set the foreign key pragma to true
//            configureForeignKeyEnforcement(connection);
            connection.setAutoCommit(false);
            return connection;
        } catch(SQLException exception){
            exception.printStackTrace();
            throw new CreateConnectionFail("Failed to create exception");
        }
    }

    public static void configureForeignKeyEnforcement(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()){
            String sql = "PRAGMA foreign_keys = true";
            statement.execute(sql);
        }
    }

    public static void main(String[] args) {
        getManualCommitConnection();
    }

}
