package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Customer {

    public String cf;

    public String name;

    public String surname;

    public String dob;

    public String email;

    public String pwd;

    public String accountType;

    public Customer(String cf, String name, String surname, String dob, String email, String pwd) {
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.pwd = pwd;
        this.accountType = "CUSTOMER";
    }

    public String getCf() {
        return cf;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDob() { return dob; }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }
}
