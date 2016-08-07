package com.github.guliash.playlist.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;

import java.lang.annotation.Inherited;

public class DeviceStateResolverImpl implements DeviceStateResolver {

    private Context mContext;
    private AudioManager mAudioManager;
    private PackageManager mPackageManager;

    public DeviceStateResolverImpl(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        this.mPackageManager = mContext.getPackageManager();
    }

    @Override
    public boolean areHeadphonesPluggedIn() {
        return mAudioManager.isWiredHeadsetOn();
    }

    @Override
    public Intent getLaunchIntentIfPackageInstalled(String name) {
        return mPackageManager.getLaunchIntentForPackage(name);
    }

    @Override
    public boolean isPackageInstalled(String name) {
        try {
            mPackageManager.getPackageInfo(name, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo(String packageName) {
        ApplicationInfo app = null;
        try {
            app = mPackageManager.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return app;
    }
}
