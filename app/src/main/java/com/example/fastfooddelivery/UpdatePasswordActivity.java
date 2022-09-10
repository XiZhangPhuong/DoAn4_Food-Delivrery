package com.example.fastfooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fastfooddelivery.Database.UserDatabase;
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
                String password = edt_new_password.getText().toString().trim();
                if (TextUtils.isEmpty(password) || password.length() < 6){
                    Toast.makeText(UpdatePasswordActivity.this,"Mat kau va tai khoan phai lon hon 6 ky tu\n fullname phai lon hon 10 ky tu",Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = getIntent().getStringExtra("phone_key");
                User user = UserDatabase.getInstance(UpdatePasswordActivity.this).userDAO().checkPhone(phone);
                user.setPassWord(password);
                UserDatabase.getInstance(UpdatePasswordActivity.this).userDAO().updateUser(user);
                Toast.makeText(UpdatePasswordActivity.this,"Update Password Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdatePasswordActivity.this, LoginAndSingupActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}