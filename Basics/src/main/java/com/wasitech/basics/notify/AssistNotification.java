package com.wasitech.basics.notify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

public abstract class AssistNotification {

    protected final Context context;
    protected final NotificationManager manager;

    public AssistNotification(Context context) {
        this.context = context;
        manager = context.getSystemService(NotificationManager.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotiChannel(channelId(), importance());
        }
    }

    public NotificationManager getManager() {
        return manager;
    }

    protected abstract int importance();

    protected abstract String channelId();

    private void NotiChannel(String channel_id, int importance) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_id.toLowerCase(), channel_id, importance);
            manager.createNotificationChannel(channel);
        }
    }

}
