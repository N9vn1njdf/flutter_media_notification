package com.example.medianotification;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.app.NotificationCompat.MediaStyle;

/** MediaNotificationPlugin */
public class MediaNotificationPlugin implements MethodCallHandler {
    private static final String CHANNEL_ID = "media_notification";
    private final Registrar registrar;

    private MediaNotificationPlugin(Registrar registrar) {
        this.registrar = registrar;
//        this.registrar.addNewIntentListener(this);
    }

    /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
      MediaNotificationPlugin plugin = new MediaNotificationPlugin(registrar);

      final MethodChannel channel = new MethodChannel(registrar.messenger(), "media_notification");
    channel.setMethodCallHandler(new MediaNotificationPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
      createNotificationChannel();

      if (call.method.equals("getPlatformVersion")) {
      result.success("Android sd " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }




//      mNotificationManager.notify();
  }

    private void createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "test";
            String description = "desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =
                    registrar.context().getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }





//        // Create a new MediaSession
//        final MediaSession mediaSession = new MediaSession(this, "debug tag");
//        // Update the current metadata
//        mediaSession.setMetadata(new MediaMetadata.Builder()
//                .putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART, artwork)
//                .putString(MediaMetadata.METADATA_KEY_ARTIST, "Pink Floyd")
//                .putString(MediaMetadata.METADATA_KEY_ALBUM, "Dark Side of the Moon")
//                .putString(MediaMetadata.METADATA_KEY_TITLE, "The Great Gig in the Sky")
//                .build());
//        // Indicate you're ready to receive media commands
//        mediaSession.setActive(true);
//        // Attach a new Callback to receive MediaSession updates
//        mediaSession.setCallback(new MediaSession.Callback() {
//
//            // Implement your callbacks
//
//        });
//        // Indicate you want to receive transport controls via your Callback
//        mediaSession.setFlags(MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);









        Bitmap art = null;
//        art = BitmapFactory.decodeResource(android.getResources(), R.mipmap.ic_launcher);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(registrar.context(), CHANNEL_ID)
            .setShowWhen(false)
            .setSmallIcon(android.R.drawable.alert_dark_frame)
//            .setLargeIcon(art)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setContentInfo("Player")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(android.R.drawable.ic_media_previous, "prev", retreivePlaybackAction(3))
            .addAction(android.R.drawable.ic_media_pause, "pause", retreivePlaybackAction(1))
            .addAction(android.R.drawable.ic_media_next, "next", retreivePlaybackAction(2))
            // Set the intent that will fire when the user taps the notification
//          .setContentIntent(pendingIntent)
//          .setAutoCancel(true);
            .setColor(0xFFDB4437)
        .setStyle(
                new MediaStyle()
//                .setMediaSession(mediaSession.getSessionToken())
        )
;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(registrar.context());
        notificationManager.notify(33, mBuilder.build());
    }

    private PendingIntent retreivePlaybackAction(int i) {
      return null;
    }
}
