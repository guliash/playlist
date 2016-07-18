package com.github.guliash.playlist.utils;

import android.content.Intent;

public interface DeviceStateResolver {

    String YANDEX_RADIO_PACKAGE = "ru.yandex.radio";
    String YANDEX_MUSIC_PACKAGE = "ru.yandex.music";

    boolean areHeadphonesPluggedIn();

    Intent getLaunchIntentIfPackageInstalled(String name);

}
