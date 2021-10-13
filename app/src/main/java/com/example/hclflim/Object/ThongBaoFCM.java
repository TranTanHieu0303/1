package com.example.hclflim.Object;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.example.hclflim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

public class ThongBaoFCM extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        String title  = remoteMessage.getNotification().getTitle();
        String text = remoteMessage.getNotification().getBody();
        final  String CHANNEL_ID = "HEADS_UP_NOTIFICATION";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Heads up  notification", NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notication = new Notification.Builder(this,CHANNEL_ID).setContentTitle(title)
                .setContentText(text).setSmallIcon(R.drawable.logo_3).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,notication.build());
        super.onMessageReceived(remoteMessage);
    }
}
