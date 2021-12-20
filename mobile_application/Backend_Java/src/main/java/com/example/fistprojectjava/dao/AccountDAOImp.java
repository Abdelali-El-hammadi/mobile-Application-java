package com.example.fistprojectjava.dao;


import com.example.fistprojectjava.model.Account;
import org.springframework.stereotype.Repository;
import com.example.fistprojectjava.model.Error;
import java.sql.*;


@Repository
public class AccountDAOImp implements AccountDAO{

    @Override
    public boolean checkAccountUnicity(String  cin,Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where(cin=?)");
            preparedStatement.setString(1,cin);
            int result= preparedStatement.executeUpdate();
            return result==0;
        }
        catch(Exception error){
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public Error createAccount(Account account) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "amidlune-bac-sram_1994");

            boolean uniqueCIN=checkAccountUnicity(account.getCin(),connection);
            if(uniqueCIN){
                PreparedStatement preparedStatement=connection.prepareStatement("insert into account(fullname,cin,phonenumber,password) values(?,?,?,?)");
                preparedStatement.setString(1,account.getFullname());
                preparedStatement.setString(1,account.getCin());
                preparedStatement.setString(1,account.getPhoneNumber());
                preparedStatement.setString(1,account.getPassword());
                preparedStatement.executeUpdate();
            }

            connection.close();
            return new Error();

        }
        catch(Exception error){
            error.printStackTrace();
            return new Error("There is a problem with the datebase");
        }
    }

    @Override
    public Account getAccount(int id) {
        Account account=new Account(-1,"","","","");
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "amidlune-bac-sram_1994");
            PreparedStatement preparedStatement=connection.prepareStatement("select * from account where(id=?) limit 1");
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                account=new Account(rs.getInt("id"),
                        rs.getString("fullname"),
                        rs.getString("cin"),
                        rs.getString("phonenumber"),
                        rs.getString("password"));
            }
        }
        catch(Exception error){
            error.printStackTrace();
        }
        return account;
    }

    @Override
    public int login(String cin,String password) {
        int accountID = -1;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "amidlune-bac-sram_1994");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from account where(cin=? and password=?)");
            preparedStatement.setString(1, cin);
            preparedStatement.setString(1, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                accountID = rs.getInt("id");
            }

            connection.close();
        }
        catch (Exception error) {
            error.printStackTrace();

        }

        finally {
            return accountID;
        }
    }
}
