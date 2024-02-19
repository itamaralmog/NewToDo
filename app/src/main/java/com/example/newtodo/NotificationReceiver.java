package com.example.newtodo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // When the broadcast is received, show the notification
        showNotification(context, "Reminder");
    }

    private void showNotification(Context context, String title) {
        // Get the system service for managing notifications
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Define the notification channel ID
        String channelId = "my_channel_id";

        // Create a notification channel for devices running Android Oreo (API 26) and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Display the notification
        notificationManager.notify(1, builder.build());
    }
}
