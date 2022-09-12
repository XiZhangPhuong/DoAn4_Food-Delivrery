package com.example.fastfooddelivery.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


public class User {

    private int ID;
    private String FullName;
    private String PhoneNumber;
    private String PassWord;

    public User(int ID,String FullName, String PhoneNumber, String PassWord) {
        this.ID = ID;
        this.FullName = FullName;
        this.PhoneNumber = PhoneNumber;
        this.PassWord = PassWord;
    }
    public User(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
