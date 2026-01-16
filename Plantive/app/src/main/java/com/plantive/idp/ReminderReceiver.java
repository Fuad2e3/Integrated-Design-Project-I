package com.plantive.idp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {
    // Changed Channel ID to force system to create a new channel with updated settings
    private static final String CHANNEL_ID = "PLANT_CARE_ALERTS_V3";

    @Override
    public void onReceive(Context context, Intent intent) {
        String taskTitle = intent.getStringExtra("taskTitle");
        Log.d("ReminderReceiver", "Alarm received for: " + taskTitle);
        showNotification(context, taskTitle);
    }

    private void showNotification(Context context, String title) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        // Use Alarm sound instead of Notification sound for better visibility
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        if (alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Plantive Care Alerts", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Loud reminders for plant care");
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 500, 200, 500});
            
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
            channel.setSound(alarmSound, audioAttributes);
            
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("Time to Care for Your Plant!")
                .setContentText(title)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setSound(alarmSound)
                .setVibrate(new long[]{0, 500, 200, 500})
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setFullScreenIntent(pendingIntent, true); // Pop up even if screen is locked

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
