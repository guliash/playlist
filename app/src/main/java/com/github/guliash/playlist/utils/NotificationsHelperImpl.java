package com.github.guliash.playlist.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.receivers.NotificationActionReceiver;

import javax.inject.Inject;

public class NotificationsHelperImpl implements NotificationsHelper {

    private DeviceStateResolver mDeviceStateResolver;
    private Context mContext;

    private static final int NOTIFICATION_ID = 0;

    @Inject
    public NotificationsHelperImpl(Context context, DeviceStateResolver deviceStateResolver) {
        this.mContext = context;
        this.mDeviceStateResolver = deviceStateResolver;
    }

    @Override
    public void showMusicAppsNotification() {

        if(!mDeviceStateResolver.areHeadphonesPluggedIn()) {
            return;
        }

        boolean musicInstalled = mDeviceStateResolver.isPackageInstalled(YANDEX_MUSIC_PACKAGE);
        boolean radioInstalled = mDeviceStateResolver.isPackageInstalled(YANDEX_RADIO_PACKAGE);

        if(!musicInstalled && !radioInstalled) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(mContext.getString(R.string.notification_content_title))
                .setContentText(mContext.getString(R.string.notification_content_text));


        if(musicInstalled) {
            Intent intent = new Intent(mContext, NotificationActionReceiver.class);
            intent.putExtra(ACTION_TYPE, YANDEX_MUSIC_TYPE);
            PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            NotificationCompat.Action action = new NotificationCompat.Action(0,
                    mContext.getString(R.string.yandex_music), pi);
            builder.addAction(action);
        }
        if(radioInstalled) {
            Intent intent = new Intent(mContext, NotificationActionReceiver.class);
            intent.putExtra(ACTION_TYPE, YANDEX_RADIO_TYPE);
            PendingIntent pi = PendingIntent.getBroadcast(mContext, 1, intent, 0);
            NotificationCompat.Action action = new NotificationCompat.Action(0,
                    mContext.getString(R.string.yandex_radio), pi);
            builder.addAction(action);
        }

        builder.setContentIntent(PendingIntent.getActivity(mContext, 0,
                (musicInstalled
                        ? mDeviceStateResolver.getLaunchIntentIfPackageInstalled(YANDEX_MUSIC_PACKAGE)
                        : mDeviceStateResolver.getLaunchIntentIfPackageInstalled(YANDEX_RADIO_PACKAGE)),
                0));
        builder.setAutoCancel(true);

        getNotificationManager().notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public void removeMusicAppsNotification() {
        getNotificationManager().cancel(NOTIFICATION_ID);
    }

    @Override
    public void onActionChose(String type) {
        removeMusicAppsNotification();
        loadActivityOfType(type);
    }

    //TODO enums?
    private void loadActivityOfType(String type) {
        switch (type) {
            case YANDEX_MUSIC_TYPE:
                mContext.startActivity(mDeviceStateResolver.getLaunchIntentIfPackageInstalled(YANDEX_MUSIC_PACKAGE));
                break;
            case YANDEX_RADIO_TYPE:
                mContext.startActivity(mDeviceStateResolver.getLaunchIntentIfPackageInstalled(YANDEX_RADIO_PACKAGE));
                break;
            default:
                throw new AssertionError("No way...");
        }
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
