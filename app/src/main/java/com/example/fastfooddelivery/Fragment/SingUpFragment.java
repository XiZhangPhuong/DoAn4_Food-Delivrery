package com.example.fastfooddelivery.Fragment;

import static com.example.fastfooddelivery.TEMP.ranDomIDUser;
import static com.example.fastfooddelivery.TEMP.showNotification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fastfooddelivery.Database.UserDatabase;
import com.example.fastfooddelivery.LoginAndSingupActivity;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.Model.User;
import com.example.fastfooddelivery.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SingUpFragment extends Fragment {
    private View mView;
    private EditText edt_fullname,edt_phonenumber,edt_password,edt_conf;
    private Button btn_singup;
    List<User> listuser= new ArrayList<>();
    public static DatabaseReference dataUser  = FirebaseDatabase.getInstance("https://fastfooddelivery-646b3-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("USER");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_singup, container, false);
        edt_fullname = mView.findViewById(R.id.edt_fullname);
        edt_phonenumber = mView.findViewById(R.id.edt_phonenumber);
        edt_password = mView.findViewById(R.id.edt_password);
        edt_conf = mView.findViewById(R.id.edt_confirmpassword);
        btn_singup = mView.findViewById(R.id.btnsingup);


        getDataUserFromFireBase();
        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = edt_fullname.getText().toString().trim();
                String phonenumber = edt_phonenumber.getText().toString().trim();
                String password = edt_password.getText().toString().trim();
                String confirm = edt_conf.getText().toString().trim();
                if(edt_fullname.length()==0){
                    Toast.makeText(getContext(),"Bạn chưa nhập họ tên",Toast.LENGTH_SHORT).show();
                    edt_fullname.setError("Họ tên trống");
                    return;
                }
                if(edt_phonenumber.length()==0){
                    Toast.makeText(getContext(),"Bạn chưa nhập họ SĐT",Toast.LENGTH_SHORT).show();
                    edt_phonenumber.setError("SDT trống");
                    return;
                }
                if(edt_phonenumber.length()!=10){
                    Toast.makeText(getContext(),"SĐT phải 10 số",Toast.LENGTH_SHORT).show();
                    edt_phonenumber.setError("SĐT phải 10 số");
                    return;
                }
                if(edt_password.length()==0){
                    Toast.makeText(getContext(),"Bạn chưa nhập mật khẩu",Toast.LENGTH_SHORT).show();
                    edt_password.setError("Mật khẩu trống");
                }
                if(edt_password.length() < 6){
                    Toast.makeText(getContext(),"Mật khẩu phải lớn hơn 6 ký tự",Toast.LENGTH_SHORT).show();
                    edt_password.setError("Nhập lại mật khẩu");
                }
                if(!edt_password.getText().toString().equals(edt_conf.getText().toString())){
                    Toast.makeText(getContext(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                    edt_conf.setError("Mật khẩu không khớp");
                }
                if(checkPhoneNumber(phonenumber)==1){
                    Toast.makeText(getContext(),"Tài khoản đã được đăng kí",Toast.LENGTH_SHORT).show();
                    edt_phonenumber.setError("Vui lòng đổi SĐT");
                    edt_phonenumber.setText("");
                }else if(checkPhoneNumber(phonenumber)==0){
                    User user = new User(fullname,phonenumber,password);
                    dataUser.child(phonenumber).setValue(user);
                    Toast.makeText(getContext(),"Đăng ký tài khoản thành công",Toast.LENGTH_LONG).show();
                    edt_fullname.setText("");
                    edt_phonenumber.setText("");
                    edt_password.setText("");
                    edt_conf.setText("");
                    LoginAndSingupActivity.viewPager2.setCurrentItem(0);
                    showNotification1();

                }
//                if (TextUtils.isEmpty(fullname) ||TextUtils.isEmpty(phonenumber)||TextUtils.isEmpty(password)||TextUtils.isEmpty(confirm)
//                ||fullname.length() < 10 ||phonenumber.length() !=10 || password.length() < 6 || confirm.length() < 6){
//                    Toast.makeText(getContext(),"Mat kau va tai khoan phai lon hon 6 ky tu\n fullname phai lon hon 10 ky tu",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!password.equals(confirm)){
//                    Toast.makeText(getContext(),"Mat khau khong trung khop",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<User> user1 = UserDatabase.getInstance(getActivity()).userDAO().getAllUser();
//                if(user1.size()==0) {
//                    User user = new User(fullname,phonenumber,password);
//                    UserDatabase.getInstance(getContext()).userDAO().InsertUser(user);
//                    Toast.makeText(getContext(),"Them user thanh cong",Toast.LENGTH_SHORT).show();
//                    LoginAndSingupActivity.viewPager2.setCurrentItem(0);
//                    return;
//                }
//                try {
//                    User u = UserDatabase.getInstance(getActivity()).userDAO().checkPhone(phonenumber);
//                    if (u.getPhoneNumber().equals(phonenumber)) {
//                        Toast.makeText(getContext(), "Vui long nhap lai user khac", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }catch (Exception e){
//
//                }
//                User user = new User(fullname,phonenumber,password);
//                UserDatabase.getInstance(getContext()).userDAO().InsertUser(user);
//                Toast.makeText(getContext(),"Them user thanh cong",Toast.LENGTH_SHORT).show();
                 // Push data realtime database

            }



        });
        return mView;
    }

    public  int checkPhoneNumber(String phone){
        for(User user : listuser){
            if(user.getPhoneNumber().equals(phone)){
                return  1;
            }
        }
        return 0;
    }

       public List<User> getDataUserFromFireBase(){
           listuser = new ArrayList<>();
        dataUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    listuser.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return listuser;
    }

    private void pushDataListUser(){
        List<User> a = new ArrayList<>();
        a.add(new User("Phạm Văn Phương","0398797284","123456"));
        a.add(new User("Ngô Trung Hiếu","0398797281","123456"));
        a.add(new User("Lê Mạnh Bin","0398797284","123456"));
        a.add(new User("Nguyễn Hoài Nhật","1234567899","123456"));
        a.add(new User("Huyền Kim Lâm","05236547895","123456"));
        a.add(new User("Lê Mạnh Hữu","0452145854","123456"));
        a.add(new User("Lê Thành Đạt","03652123458","123456"));
        a.add(new User("Nguyễn Huyền Lưu","14523658920","123456"));
        a.add(new User("Nguyễn Thị Huyền","0124581251","123456"));
        a.add(new User("Kim Chang Long","1245785214","123456"));
        a.add(new User("Lê Quốc Long","0452145896","123456"));
        a.add(new User("Nguyễn Thị Na Uy","03562145896","123456"));
        a.add(new User("Lý La Long","0123456785","123456"));
        a.add(new User("Lê Quốc Bảo","0425632145","123456"));
        a.add(new User("Lê Thị Nhung","0365214526","123456"));
        a.add(new User("Lê Hữu Hoài","0123456215","123456"));
        a.add(new User("Phạm Hữu Gia","0689215621","123456"));
        a.add(new User("Nguyễn Mạnh Lâm","0458921452","123456"));
        a.add(new User("Lý Gia Kiệt","1452015921","123456"));
        a.add(new User("Lê Thị Quyền","0123456896","123456"));
        a.add(new User("Trương Đình Quyền","0394125638","123456"));
        a.add(new User("Trần Việt Đức","0325412562","123456"));

        for(User u : a){
            dataUser.child(u.getPhoneNumber()).child(u.getFullName()+u.getPhoneNumber()+u.getPassWord());
        }
    }


    private void showNotification1(){
        showNotification(getActivity(),"ADmin","hello");

    }

}