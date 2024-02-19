package com.example.newtodo;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ServiceForNotification extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        // When the service is created, show the notification
        showNotification();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // Return null because this service does not support binding
        return null;
    }

    private void showNotification() {
        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channelId")
                .setContentTitle("Reminder")
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Set the small icon (replace with your own)
                .setContentText("Notification Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Get the notification manager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Check if permission to post notifications is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            // If permission is not granted, return without showing the notification
            return;
        }

        // Show the notification
        notificationManager.notify(1, builder.build());
    }
}
