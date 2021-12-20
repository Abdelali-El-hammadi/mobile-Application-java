package com.example.fistprojectjava.dao;

import com.example.fistprojectjava.model.Account;
import com.example.fistprojectjava.model.Error;

import java.sql.Connection;


public interface AccountDAO {

    public boolean checkAccountUnicity(String cin, Connection connection);

    public Error createAccount(Account account);

    public Account getAccount(int id);

    public int login(String cin,String password);
}
