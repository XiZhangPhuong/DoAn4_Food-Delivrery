package com.example.fastfooddelivery.ShowNotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notificationn extends Application {
    public  static final String CHANNEL_ID = "CHANNEL_ID";
    public  static final String MESS = "MESS";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    public void  createNotificationChannel(){
        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,MESS, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(MESS);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


}
