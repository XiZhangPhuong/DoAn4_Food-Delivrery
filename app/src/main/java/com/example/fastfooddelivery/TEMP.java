package com.example.fastfooddelivery;

import static com.example.fastfooddelivery.ShowNotification.Notificationn.CHANNEL_ID;
import static com.example.fastfooddelivery.ShowNotification.Notificationn.MESS;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fastfooddelivery.Model.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TEMP {
    public static List<Notification> listNoti = new ArrayList<>();
    public static NotificationCompat.Builder notificationn;
    public static NotificationCompat notificationCompat;

    public static int ranDomIDUser(){
        Random random = new Random();
        int s = (int) Math.floor(((Math.random() * 899999) + 100000));
        return s;
    }

    public static void showNotification(Context context,String title,String mess){

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_favorite_24)
            .setContentTitle(title)
            .setContentText(mess)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());

    }

}
