package com.revature.utility;

import com.revature.exceptions.CreateConnectionFail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String url = System.getenv("DATABASE-PATH");

    public static Connection getAutoCommitConnect(){
        try{
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch(SQLException exception){
            exception.printStackTrace();
            throw new CreateConnectionFail("Failed to create exception");
        }

    }

    public static void main(String[] args) {
        getAutoCommitConnect();
    }

}
