package com.revature.repository;

import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonRepo {

    public static void main(String[] args) {
        PersonRepo repo = new PersonRepo();
//        repo.createPeopleTable();
        repo.createPersonRecord("Slagathor", -22032);
    }

    public void createPeopleTable(){
        try(Connection connection = ConnectionFactory.getAutoCommitConnect()){
            String query = "create table people(\n" +
                    "\tid integer primary key,\n" +
                    "\tname text not null,\n" +
                    "\tage integer check(age >= 0)\n" +
                    ")";
            Statement simpleStatement = connection.createStatement();

            /*
                We have three options for how to execute the query:
                execute()           -> returns a boolean that tells us whether we got back a
                                    ResultSet or int indicating the # of rows affected
                executeUpdate()     -> returns an int indicating how many rows were affected
                executeQuery()      -> returns a ResultSet with the data we searched for, if there are any matches
             */
            simpleStatement.execute(query);

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void createPersonRecord(String name, int age){
        try(Connection connection = ConnectionFactory.getAutoCommitConnect()){
            // when making PreparedStatements use ? to indicate where data will be injected into the query
            String query = "insert into People (name, age) values (?,?)";
            // use PrepparedStatements when allowing users to inject data to your database
            PreparedStatement ps = connection.prepareStatement(query);
            // note indexing of the placeholders in our query starts at 1 instead of 0
            ps.setString(1, name);
            ps.setInt(2, age);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 1){
                System.out.println("Person successfully created");
            } else {
                System.out.println("Person was NOT created: rows affected = " + rowsAffected);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }



}
