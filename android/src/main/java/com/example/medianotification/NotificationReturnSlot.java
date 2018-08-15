package com.example.medianotification;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NotificationReturnSlot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("toggle")) {
            String action = intent.getStringExtra("action");
            Log.w("action", action.equals("play") ? "OK" : "NOT OK");
            new NotificationPanel(context, action.equals("play"));
        }
    }
}

