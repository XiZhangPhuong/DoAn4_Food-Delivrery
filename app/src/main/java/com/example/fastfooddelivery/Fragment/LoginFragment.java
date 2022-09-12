package com.example.fastfooddelivery.Fragment;

import static com.example.fastfooddelivery.TEMP.checkConnectInternet;
import static com.example.fastfooddelivery.TEMP.ranDomCODE;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.fastfooddelivery.LoginAndSingupActivity;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.User;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.DataSharedPreferences;
import com.example.fastfooddelivery.VerifyActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LoginFragment extends Fragment {
    public static final String KEY_OBJECT = "KEY_OBJECT";
    private static List<User> listuser = new ArrayList<>();
    private View mView;
    private LoginAndSingupActivity activity;
    private EditText edtusername,edtpassword;
    private Button btnlogin;
    private TextView tv_forgot;
    public static DatabaseReference dataUser = FirebaseDatabase.getInstance().getReference("USER");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_login, container, false);
        activity = (LoginAndSingupActivity) getActivity();
        tv_forgot = mView.findViewById(R.id.forgotpassword);
        edtusername = mView.findViewById(R.id.username);
        edtpassword = mView.findViewById(R.id.password);
        btnlogin = mView.findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  if(checkConnectInternet(getContext())==true){
                      loGinWithFireBase();
                  }else{
                      Toast.makeText(getContext(),"Không có kết nối INTERNET",Toast.LENGTH_LONG).show();
                  }
//                List<User> list = new ArrayList<>();
//                list = UserDatabase.getInstance(getContext()).userDAO().getAllUser();
//                for (User user : list){
//                    if (user.getPhoneNumber().equals(edtusername.getText().toString().trim()) && user.getPassWord().equals(edtpassword.getText().toString().trim()) ){
//                        ProgressDialog dialog = new ProgressDialog(getContext());
//                        dialog.setMessage("Please wait...");
//
//                        dialog.show();
//                        Runnable runnable = new Runnable() {
//                            @Override
//                            public void run() {
//                                Intent intent = new Intent(activity, MainActivity.class);
//                                startActivity(intent);
//                                activity.finishAffinity();
//                                DataSharedPreferences.setUser(getContext(),user,KEY_OBJECT);
//
//                            }
//                        };
//                        Handler handler = new Handler();
//                        handler.postDelayed(runnable,5000);
//
//                    }
//                }
//                new AlertDialog.Builder(getContext()).setTitle("Warring")
//                        .setMessage("Sai toan khoan hoac mat khau").setPositiveButton("ok",null).show();

            }
        });
        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),VerifyActivity.class);
                startActivity(intent);
            }
        });
        return mView;
    }
    private void loGinWithFireBase(){
        String phone = edtusername.getText().toString().trim();
        String pass = edtpassword.getText().toString();
        dataUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    listuser.add(user);
                }
                if(checkUser(phone,pass)==true){
                   // User u = new User(phone,pass,null);
                   // DataSharedPreferences.setUser(getContext(),u,KEY_OBJECT);
                    ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setMessage("Please wait...");
                    dialog.show();

                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();;
                            activity.finishAffinity();
                           // DataSharedPreferences.setUser(getContext(),user,KEY_OBJECT);

                        }
                    };
                    Handler handler = new Handler();
                    handler.postDelayed(runnable,1000);
                }else{
                    Toast.makeText(getContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private boolean checkUser(String name,String pass){
        for(User user : listuser){
            if(user.getPhoneNumber().equals(name.trim()) && user.getPassWord().equals(pass)){
                return  true;
            }
        }
        return false;
    }



}