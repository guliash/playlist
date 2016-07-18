package com.github.guliash.playlist.utils;

import android.content.Intent;

public class FakeDeviceStateResolver implements DeviceStateResolver {
    @Override
    public boolean areHeadphonesPluggedIn() {
        return false;
    }

    @Override
    public Intent getLaunchIntentIfPackageInstalled(String name) {
        return null;
    }

    @Override
    public boolean isPackageInstalled(String name) {
        return false;
    }
}
