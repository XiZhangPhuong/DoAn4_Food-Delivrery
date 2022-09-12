package com.example.fastfooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fastfooddelivery.Model.User;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText edt_new_password;
    private Button btn_finishchagepass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        edt_new_password = findViewById(R.id.edt_new_password);
        btn_finishchagepass = findViewById(R.id.btn_finishchagepass);
        btn_finishchagepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}