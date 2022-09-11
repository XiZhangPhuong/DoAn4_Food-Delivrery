package com.example.fastfooddelivery;

import static com.example.fastfooddelivery.Dialog.showNotification.CHANNEL_1_ID;

import android.content.Context;
import android.media.MediaPlayer;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fastfooddelivery.Model.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TEMP {
    public static List<Notification> listNoti = new ArrayList<>();
    public static NotificationManagerCompat notificationManagerCompat;

    public static int ranDomIDUser(){
        Random random = new Random();
        int s = (int) Math.floor(((Math.random() * 899999) + 100000));
        return s;
    }
    public static void showNotification(Context context,String title,String mess){
        notificationManagerCompat  = NotificationManagerCompat.from(context);
        android.app.Notification notification = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.icon_logo_fish)
                .setContentTitle(title)
                .setContentText(mess)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(false)
                .build();

        notificationManagerCompat.notify(1,notification);
        MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.amnthanh);
        mediaPlayer.start();
    }



}
