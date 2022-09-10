package com.example.fastfooddelivery.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fastfooddelivery.Model.Food;
import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataSharedPreferences {
    private static final String MY_KEY = "MY_KEY";

    public static void setList(Context context, List<Notification> list, String Key){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, jsonString);
        editor.apply();
    }
    public static List<Notification> getList(Context context,String Key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(Key, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Notification>>(){}.getType();
        List<Notification> list = gson.fromJson(jsonString, type);
        return list;
    }
    public static void setUser(Context context, User user, String KEY){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(KEY,json);
        editor.apply();
    }
    public static  User getUser(Context context, String KEY){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY,"");
        Gson gson = new Gson();
        User user = gson.fromJson(json,User.class);
        return user;
    }
}
