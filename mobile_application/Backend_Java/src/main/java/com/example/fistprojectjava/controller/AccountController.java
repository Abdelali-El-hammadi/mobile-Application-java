package com.example.fistprojectjava.controller;

import com.example.fistprojectjava.dao.AccountDAOImp;
import com.example.fistprojectjava.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.fistprojectjava.model.Error;

@RestController

@RequestMapping("/api")

public class AccountController<personDaoImp> {

    @Autowired
    AccountDAOImp personDaoImp;

    @GetMapping(value="getAccount/{id}")
    public Account getAccount(@PathVariable("id") String id){
        return personDaoImp.getAccount(Integer.parseInt(id));
    }

    @PostMapping(value="/createAccount")
    public Error createAccount(@RequestBody Account account){
        return personDaoImp.createAccount(account);
    }

    @PostMapping(value="/login")
    public int login(@RequestBody Account account){
        return personDaoImp.login(account.getCin(), account.getPassword());
    }

}
