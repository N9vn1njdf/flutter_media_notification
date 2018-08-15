package com.example.medianotification;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.RemoteViews;


/** MediaNotificationPlugin */
public class MediaNotificationPlugin implements MethodCallHandler {
    private static final String CHANNEL_ID = "media_notification";
    private final Registrar registrar;

    private MediaNotificationPlugin(Registrar registrar) {
        this.registrar = registrar;
    }

    /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
      MediaNotificationPlugin plugin = new MediaNotificationPlugin(registrar);

      final MethodChannel channel = new MethodChannel(registrar.messenger(), "media_notification");
        channel.setMethodCallHandler(new MediaNotificationPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

      if (call.method.equals("show")) {
          show();
      } else if (call.method.equals("hide")) {
          hide();
      } else if (call.method.equals("isActive")) {
          result.success("No");
      } else {
          result.notImplemented();
      }
  }

  private NotificationPanel nPanel;

  private void show() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          int importance = NotificationManager.IMPORTANCE_DEFAULT;
          NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, importance);
          channel.enableVibration(false);
          channel.setSound(null, null);
          NotificationManager notificationManager = registrar.context().getSystemService(NotificationManager.class);
          notificationManager.createNotificationChannel(channel);
      }

      this.nPanel = new NotificationPanel(registrar.context(), true);
  }

  private void hide() {
      this.nPanel.notificationCancel();
  }
}




