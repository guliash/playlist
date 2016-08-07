package com.github.guliash.playlist.utils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;

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

    @Override
    public ApplicationInfo getApplicationInfo(String packageName) {
        return null;
    }
}
