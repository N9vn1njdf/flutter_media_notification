package com.example.medianotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by dmitry on 14.08.18.
 */

public class NotificationPanel {
    private Context parent;
    private NotificationManager nManager;
    private NotificationCompat.Builder nBuilder;
    private RemoteViews remoteView;
    private boolean play;

    public NotificationPanel(Context parent, boolean play) {
        this.parent = parent;
        this.play = play;
        nBuilder = new NotificationCompat.Builder(parent, "media_notification")
                .setContentTitle("Parking Meter")
                .setSmallIcon(android.R.drawable.alert_dark_frame)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(this.play)
                .setOnlyAlertOnce(true)
                .setVibrate(new long[]{0L})
                .setSound(null);

        remoteView = new RemoteViews(parent.getPackageName(), R.layout.notificationlayout);

        if (this.play) {
            remoteView.setTextViewText(R.id.title, "Play notification");
            remoteView.setImageViewResource(R.id.btn1, android.R.drawable.ic_media_pause);
        } else {
            remoteView.setTextViewText(R.id.title, "Pause notification");
            remoteView.setImageViewResource(R.id.btn1, android.R.drawable.ic_media_play);
        }

        //set the button listeners
        setListeners(remoteView);
        nBuilder.setContent(remoteView);

        Notification notification = nBuilder.build();
//        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        nManager = (NotificationManager) parent.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(2, notification);
    }

    public void setListeners(RemoteViews view){
        Intent intent = new Intent(parent, NotificationReturnSlot.class);
        intent.setAction("toggle");

        if (this.play) {
            intent.putExtra("action", "pause");
        } else {
            intent.putExtra("action","play");
        }

//        intent.putExtra("extra", "extra");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(parent, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btn1, pendingIntent);

//        Intent intent=new Intent(parent, NotificationReturnSlot.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        intent.putExtra("DO", "volume");
    }


    public void notificationCancel() {
        nManager.cancel(2);
    }
}

