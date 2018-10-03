package vn.nms.core.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import vn.nms.core.BuildConfig;
import vn.nms.core.R;
import vn.nms.core.base.BaseApp;

public class NotificationUtils {
    public static void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "App Notification";
            String description = "App Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(BuildConfig.APPLICATION_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = BaseApp.getAppContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public static void showNotification(String title, String content) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                BaseApp.getAppContext(), BuildConfig.APPLICATION_ID)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(BaseApp.getAppContext());
        notificationManager.notify(1, mBuilder.build());
    }

}
