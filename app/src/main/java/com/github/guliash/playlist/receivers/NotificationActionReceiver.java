package com.github.guliash.playlist.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.utils.NotificationsHelper;

/**
 * Receiver for catching notifications actions
 */
public class NotificationActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //closing notification drawer manually after action, because receiver used
        context.sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

        String type = intent.getExtras().getString(NotificationsHelper.ACTION_TYPE);
        if(TextUtils.isEmpty(type)) {
            return;
        }
        //TODO probably it better to move this to specific component.
        NotificationsHelper notificationsHelper = ((PlaylistApplication)context.getApplicationContext())
                .getAppComponent().notificationsHelper();

        notificationsHelper.onActionChose(type);
    }
}
