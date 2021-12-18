package com.IlhamJmartMH;
import com.IlhamJmartMH.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Account sebagai model object dari akun
 * @author Muhammad Ilham M S
 * @version 16 Desember 2021
 */
public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public Store store;

    /**
     * Method sebagai inisialisasi data account
     */
    public Account(String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    
    public String toString() {
        return  "name : " + this.name + 
                "\nemail : " + this.name + 
                "\npassword : " + this.password;
    }
}
