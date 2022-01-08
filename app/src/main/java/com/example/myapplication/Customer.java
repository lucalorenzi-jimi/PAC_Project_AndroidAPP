package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Customer {
    @SerializedName(cf)
    public String cf;
    @SerializedName(name)
    public String name;
    @SerializedName(surname)
    public String surname;
    @SerializedName(dob)
    public Date dob;
    @SerializedName(email)
    public String email;
    @SerializedName(pwd)
    public String pwd;

    public Customer(String cf, String name, Date dob, String email) {
        this.cf = cf;
        this.name = name;
        this.dob = dob;
        this.email = email;
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

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }
}
