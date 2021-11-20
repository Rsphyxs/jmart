package com.IlhamJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store
{
    public String name;
    public String address;
    public String phoneNumber;
    public double balance;
    static final String REGEX_NAME = "^\\d{9,12}$";
    static final String REGEX_PHONE = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    
    public Store(String name, String address, String phoneNumber, double balance){
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
    
    public String toString() {
        return  "name: " + this.name + 
                "\naddress: " + this.address + 
                "\nahoneNumber: " + this.phoneNumber;
    }
    
    public boolean validate(){
        Pattern REGEX_NAME = Pattern.compile(this.REGEX_NAME);
        Matcher NameValidate = REGEX_NAME.matcher(this.name);
        boolean nameValidate = NameValidate.find();
        Pattern REGEX_PHONE = Pattern.compile(this.REGEX_PHONE);
        Matcher NumberValidate = REGEX_PHONE.matcher(this.name);
        boolean numberValidate = NumberValidate.find();
        
        if(nameValidate == true && numberValidate == true){
            return true;
        }
        else{
            return false;
        }
    }
}
