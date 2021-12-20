package com.example.fistprojectjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String fullname;
    private String cin;
    private String phoneNumber;
    private String password;
}
