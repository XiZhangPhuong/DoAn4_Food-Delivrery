package com.example.fastfooddelivery.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fastfooddelivery.Model.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MySharedPreferences {
    private static final String MY_KEY = "MY_KEY";
    private static final String STRING_KEY = "STRING_KEY";

    public static void setList(Context context, List<Food> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STRING_KEY, jsonString);
        editor.apply();
    }
    public static List<Food> getList(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_KEY, Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString(STRING_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Food>>(){}.getType();
        List<Food> list = gson.fromJson(jsonString, type);
        return list;
    }
}
