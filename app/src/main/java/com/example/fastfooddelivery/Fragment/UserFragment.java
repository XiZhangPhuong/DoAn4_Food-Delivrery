package com.example.fastfooddelivery.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfooddelivery.Activity.NotificationActivity;
import com.example.fastfooddelivery.Adapter.UserIconAdapter;
import com.example.fastfooddelivery.LoginAndSingupActivity;
import com.example.fastfooddelivery.MainActivity;
import com.example.fastfooddelivery.Model.IconUser;
import com.example.fastfooddelivery.Model.User;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.DataSharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.fastfooddelivery.Fragment.LoginFragment.KEY_OBJECT;


public class UserFragment extends Fragment {
    private View mView;
    private RecyclerView rcv_icon_user;
    UserIconAdapter adapter;
    MainActivity mMainActivity;
    private TextView tv_logout,tv_name_user,tv_phone_user,tv_id_user,tv_yourorder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_user, container, false);
        mMainActivity = (MainActivity) getActivity();
        rcv_icon_user = mView.findViewById(R.id.tcv_icon_user);
        tv_logout = mView.findViewById(R.id.tv_logout);
        tv_name_user = mView.findViewById(R.id.tv_name_user);
        tv_phone_user = mView.findViewById(R.id.tv_phone_user);
        tv_id_user = mView.findViewById(R.id.tv_id_user);
        tv_yourorder = mView.findViewById(R.id.tv_yourorder);
        List<IconUser> list = new ArrayList<>();
        list.add(new IconUser(R.drawable.ic_bookmark,"Bookmark"));
        list.add(new IconUser(R.drawable.ic_notifications,"Notification"));
        list.add(new IconUser(R.drawable.ic_settings,"Setting"));
        list.add(new IconUser(R.drawable.ic_payment,"Pay"));
        adapter = new UserIconAdapter(list, new UserIconAdapter.IClickIconUser() {
            @Override
            public void Click(int i) {
                switch (i){
                    case 0:

                        break;
                    case 1:
                        Intent intent = new Intent(mMainActivity, NotificationActivity.class);
                        startActivity(intent);
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                }
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mMainActivity,2);
        rcv_icon_user.setLayoutManager(gridLayoutManager);
        rcv_icon_user.setAdapter(adapter);

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mMainActivity).setTitle("LOG OUT")
                        .setMessage("Are you sure?").setIcon(R.drawable.bg_fastfood)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(mMainActivity, LoginAndSingupActivity.class);
                                startActivity(intent);
                                mMainActivity.finish();
                            }
                        }).setNegativeButton("no",null).show();
            }
        });

        tv_yourorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        User user = DataSharedPreferences.getUser(mMainActivity,KEY_OBJECT);
        tv_name_user.setText(user.getFullName().toUpperCase());
        tv_phone_user.setText(user.getPhoneNumber());
        tv_id_user.setText("ID:"+user.getID());
        return mView;
    }
}