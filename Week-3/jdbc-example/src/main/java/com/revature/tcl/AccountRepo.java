package com.revature.tcl;

import com.revature.utility.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountRepo {

    public static void main(String[] args) {
        Account fromAccount = new Account(1, 100f);
        Account toAccount = new Account(2, 200f);
        transferMoney(fromAccount, toAccount, 55f);
    }

    public static void transferMoney(Account from, Account to, float amount){
        String addSQL = "update accounts set balance = balance + ? where id = ?";
        String withdrawSQL = "update accounts set balance = balance - ? where id = ?";
        try(Connection connection = ConnectionFactory.getManualCommitConnection()){
            try(PreparedStatement ps = connection.prepareStatement(addSQL)){
                ps.setFloat(1,amount);
                ps.setInt(2,to.id);
                int rowCount = ps.executeUpdate();
                if(rowCount != 1){
                    connection.rollback();
                    throw new SQLException("Adding money to account failed");
                }
            }
            try(PreparedStatement ps2 = connection.prepareStatement(withdrawSQL)){
                ps2.setFloat(1,amount);
                ps2.setInt(2,from.id);
                int rowCount = ps2.executeUpdate();
                if(rowCount != 1){
                    connection.rollback();
                    throw new SQLException("Withdrawing money from account failed");
                }
            }
            connection.commit();
        } catch (SQLException exception){
            exception.printStackTrace();
        }

    }

}
