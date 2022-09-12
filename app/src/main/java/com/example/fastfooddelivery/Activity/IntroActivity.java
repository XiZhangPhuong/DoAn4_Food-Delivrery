package com.example.fastfooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fastfooddelivery.LoginAndSingupActivity;
import com.example.fastfooddelivery.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, LoginAndSingupActivity.class);
                startActivity(intent);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,5000);
    }
}