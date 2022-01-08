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
