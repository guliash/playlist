package com.github.guliash.playlist.utils;

/**
 * Helper interface for notifications. Shows and hides notifications, navigates from them
 */
public interface NotificationsHelper {

    String ACTION_TYPE = "com.github.guliash.playlist.notification.action_type";

    String YANDEX_MUSIC_TYPE = "music";
    String YANDEX_RADIO_TYPE = "radio";

    String YANDEX_RADIO_PACKAGE = "ru.yandex.radio";
    String YANDEX_MUSIC_PACKAGE = "ru.yandex.music";

    /**
     * Shows notifications suggesting using the radio and music apps
     */
    void showMusicAppsNotification();

    /**
     * Removes notification from drawer
     */
    void removeMusicAppsNotification();

    /**
     * Callback when notification was closed
     * @param type type if notification
     */
    void onActionChose(String type);

}
