package com.example.fastfooddelivery.Activity;

import static com.example.fastfooddelivery.Fragment.LoginFragment.KEY_OBJECT;
import static com.example.fastfooddelivery.TEMP.listNoti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.fastfooddelivery.Adapter.NotificationAdapter;
import com.example.fastfooddelivery.Model.Notification;
import com.example.fastfooddelivery.Model.User;
import com.example.fastfooddelivery.R;
import com.example.fastfooddelivery.SharedPreferences.DataSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView rcv_notification;
    private ImageButton imb_back_notifi;
    private NotificationAdapter adapter;
    private List<Notification> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        rcv_notification = findViewById(R.id.rcv_notification);
        imb_back_notifi = findViewById(R.id.imb_back_notifi);
        imb_back_notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<Notification> list = DataSharedPreferences.getList(this,"KEY_NOTI");
        if(list == null)
            return;


        User user = DataSharedPreferences.getUser(this,KEY_OBJECT);
        for (Notification notification : list){
            if (user.getPhoneNumber().equals(notification.getPhoneNumber())){
                mList.add(notification);
            }
        }

        adapter = new NotificationAdapter(mList, new NotificationAdapter.IClickItemNoti() {
            @Override
            public void ClickItemNoti(Notification noti) {
                new AlertDialog.Builder(NotificationActivity.this).setTitle("")
                        .setMessage(""+noti.getNoti()).show();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv_notification.setLayoutManager(linearLayoutManager);
        rcv_notification.setAdapter(adapter);

    }
}