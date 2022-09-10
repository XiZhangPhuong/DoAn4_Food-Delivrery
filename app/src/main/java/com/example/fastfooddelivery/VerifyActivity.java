package com.example.fastfooddelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fastfooddelivery.Database.UserDatabase;
import com.example.fastfooddelivery.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {
    private EditText edt_code,edt_phone;
    private Button btn_verify_code,btnsendcode;
    private TextView tv_dontcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        edt_code = findViewById(R.id.edt_verify);
        edt_phone = findViewById(R.id.edt_phonenumber_verify);
        btnsendcode = findViewById(R.id.btn_sendcode);
        btn_verify_code = findViewById(R.id.btn_verify);
        tv_dontcode = findViewById(R.id.tv_dontcode);
        btnsendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = edt_phone.getText().toString().trim();
                try {
                    User u = UserDatabase.getInstance(VerifyActivity.this).userDAO().checkPhone(phoneNumber);
                    if (!u.getPhoneNumber().equals(phoneNumber)) {
                        Toast.makeText(VerifyActivity.this, "Khong co dien thoai nay", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){
                    Toast.makeText(VerifyActivity.this, "Khong co dien thoai nay", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                    edt_phone.setError("please enter valid phone");
                } else {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+84" + phoneNumber, 60, TimeUnit.SECONDS, VerifyActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    signInUser(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    edt_code.setError("Verification Failed!!");
                                }

                                @Override
                                public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);

                                    btn_verify_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String verificationCode = edt_code.getText().toString();
                                            if (verificationId.isEmpty()) return;
                                            //create a credential
                                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, verificationCode);
                                            signInUser(credential);
                                        }
                                    });
                                }
                            }
                    );
                }
            }
        });
    }

    private void signInUser(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(VerifyActivity.this, UpdatePasswordActivity.class);
                            intent.putExtra("phone_key",edt_phone.getText().toString().trim());
                            startActivity(intent);
                            finish();
                        } else {
//                             Log.d(TAG, "onComplete:"+task.getException().getLocalizedMessage());
                        }
                    }
                });
    }
}