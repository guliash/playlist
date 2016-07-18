package com.github.guliash.playlist.utils;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class DeviceStateResolverImpl implements DeviceStateResolver {

    private Context mContext;
    private AudioManager mAudioManager;

    public DeviceStateResolverImpl(Context context) {
        this.mContext = context;
        this.mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean areHeadphonesPluggedIn() {
        return mAudioManager.isWiredHeadsetOn();
    }

    @Override
    public Intent getLaunchIntentIfPackageInstalled(String name) {
        return mContext.getPackageManager().getLaunchIntentForPackage(name);
    }
}
