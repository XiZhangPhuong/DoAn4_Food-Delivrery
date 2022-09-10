package com.example.fastfooddelivery.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.Model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void InsertUser(User user);

    @Query("select * from tableuser")
    List<User> getAllUser();

    @Query("select * from tableuser where PhoneNumber = :strPhone")
    User checkPhone(String strPhone);

    @Update
    void updateUser(User user);



}
