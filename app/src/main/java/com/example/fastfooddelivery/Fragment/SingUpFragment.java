package com.example.fastfooddelivery.Fragment;


import static com.example.fastfooddelivery.TEMP.checkConnectInternet;
import static com.example.fastfooddelivery.TEMP.ranDomCODE;
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


       // getDataUserFromFireBase();
        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkConnectInternet(getContext())==true){
                    signupWithFireBase();
                }else{
                    Toast.makeText(getContext(),"Kh??ng c?? k???t n???i INTERNET",Toast.LENGTH_LONG).show();
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
    private void signupWithFireBase(){
        String fullname = edt_fullname.getText().toString().trim();
        String phonenumber = edt_phonenumber.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String confirm = edt_conf.getText().toString().trim();
        if(edt_fullname.length()==0){
            Toast.makeText(getContext(),"B???n ch??a nh???p h??? t??n",Toast.LENGTH_SHORT).show();
            edt_fullname.setError("H??? t??n tr???ng");
            return;
        }
        if(edt_phonenumber.length()==0){
            Toast.makeText(getContext(),"B???n ch??a nh???p h??? S??T",Toast.LENGTH_SHORT).show();
            edt_phonenumber.setError("SDT tr???ng");
            return;
        }
        if(edt_phonenumber.length()!=10){
            Toast.makeText(getContext(),"S??T ph???i 10 s???",Toast.LENGTH_SHORT).show();
            edt_phonenumber.setError("S??T ph???i 10 s???");
            return;
        }
        if(edt_password.length()==0){
            Toast.makeText(getContext(),"B???n ch??a nh???p m???t kh???u",Toast.LENGTH_SHORT).show();
            edt_password.setError("M???t kh???u tr???ng");
        }
        if(edt_password.length() < 6){
            Toast.makeText(getContext(),"M???t kh???u ph???i l???n h??n 6 k?? t???",Toast.LENGTH_SHORT).show();
            edt_password.setError("Nh???p l???i m???t kh???u");
        }
        if(!edt_password.getText().toString().equals(edt_conf.getText().toString())){
            Toast.makeText(getContext(),"M???t kh???u kh??ng kh???p",Toast.LENGTH_SHORT).show();
            edt_conf.setError("M???t kh???u kh??ng kh???p");
        }
        if(checkPhoneNumber(phonenumber)==1){
            Toast.makeText(getContext(),"T??i kho???n ???? ???????c ????ng k??",Toast.LENGTH_SHORT).show();
            edt_phonenumber.setError("Vui l??ng ?????i S??T");
            edt_phonenumber.setText("");
        }else if(checkPhoneNumber(phonenumber)==0){
            User user = new User(ranDomCODE(),fullname,phonenumber,password);
            dataUser.child(phonenumber).setValue(user);
            Toast.makeText(getContext(),"????ng k?? t??i kho???n th??nh c??ng",Toast.LENGTH_LONG).show();
            edt_fullname.setText("");
            edt_phonenumber.setText("");
            edt_password.setText("");
            edt_conf.setText("");
            LoginAndSingupActivity.viewPager2.setCurrentItem(0);
            //gui thong bao cho Nguoi Dung
            String title = "Xin ch??o! "+fullname.toUpperCase().toString();
            String mess = "C???m ??n b???n ???? s??? d???ng d???ch v??? c???a ch??ng t??i";
            showNotification(getContext(),title,mess);
        }
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



}