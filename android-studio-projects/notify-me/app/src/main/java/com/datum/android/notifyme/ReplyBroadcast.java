package com.datum.android.notifyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

public class ReplyBroadcast extends BroadcastReceiver {

    String text = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Build a new notification, which informs the user that the system
        // handled their interaction with the previous notification.
        Notification repliedNotification = new NotificationCompat.Builder(context, MainActivity2.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_reply_icon)
                .setContentText(getMessageText(intent))
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(MainActivity2.NOTIFICATION_ID,
                repliedNotification);

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }

    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            Log.d("TAG", "getMessageText: " + remoteInput.getCharSequence("key_text_reply"));
            text = remoteInput.getCharSequence("key_text_reply").toString();
            return text;
        }
        return null;
    }
}
