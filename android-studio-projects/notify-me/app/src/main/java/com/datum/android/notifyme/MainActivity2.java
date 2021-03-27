package com.datum.android.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.concurrent.Executor;

import static androidx.core.app.NotificationCompat.Action.SEMANTIC_ACTION_REPLY;

public class MainActivity2 extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1001;
    public static final String CHANNEL_ID = "1234";

    // Key for the string that's delivered in the action's intent.
    public static final String KEY_TEXT_REPLY = "key_text_reply";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Button notify = findViewById(R.id.notify_btn);

        notify.setOnClickListener(View -> {

            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        Thread.sleep(5000);
                        testFunciton();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        });
    }

    public void testFunciton() {


        // -------------------- just a navigation ----------------- //
//            Intent intent = new Intent(this, MainActivity2.class);
//            PendingIntent replyPendingIntent =
//                    PendingIntent.getActivity(this,
//                            0,
//                            intent,
//                            0);
//
//
//            // add action to attach the reply label with it
//            NotificationCompat.Action action =
//                    new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon,
//                            "take me to Main Activity", replyPendingIntent)
//                            .build();

        // --------------------------------------------------------- //


        // ---------------------- navigation with action ------------------- //
        // add reply label
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("type something...")
                .build();


        Intent broacastIntent = new Intent(this, ReplyBroadcast.class);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0,
                broacastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // add action to attach the reply label with it
        NotificationCompat.Action action2 =
                new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon,
                        "Reply", actionIntent)

                        .addRemoteInput(remoteInput)

                        .build();

        // ---------------------------------------------------------------------- //

        createNotificationChannel();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_active_24)
                .setContentTitle("title")
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .addAction(action2)
                .setAutoCancel(true)
                .setColor(Color.BLUE)
                .setSubText("sub-text test")
                .setPriority(NotificationCompat.PRIORITY_HIGH);


        // show the notificaiton
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel name";
            String description = "channel desc";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void mainMethod() {
        AppExecutor.getInstance().getMainThread().execute(() -> {

        });
    }

}