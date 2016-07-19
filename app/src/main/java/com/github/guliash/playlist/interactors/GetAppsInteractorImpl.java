package com.github.guliash.playlist.interactors;

import android.content.pm.ApplicationInfo;

import com.github.guliash.playlist.executors.PostExecutor;
import com.github.guliash.playlist.executors.ThreadExecutor;
import com.github.guliash.playlist.utils.DeviceStateResolver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GetAppsInteractorImpl implements GetAppsInteractor {

    private DeviceStateResolver mDeviceStateResolver;
    private ThreadExecutor mExecutor;
    private PostExecutor mPostExecutor;

    private Callbacks mCallbacks;
    private List<String> mApps;

    @Inject
    public GetAppsInteractorImpl(DeviceStateResolver deviceStateResolver, ThreadExecutor executor,
                                 PostExecutor postExecutor) {
        this.mDeviceStateResolver = deviceStateResolver;
        this.mExecutor = executor;
        this.mPostExecutor = postExecutor;
    }

    @Override
    public void execute(Callbacks callbacks, List<String> apps) {
        this.mCallbacks = callbacks;
        this.mApps = apps;
        mExecutor.execute(this);
    }

    private void onApps(final List<ApplicationInfo> apps) {
        mPostExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mCallbacks.onApps(apps);
            }
        });
    }

    @Override
    public void run() {
        List<ApplicationInfo> result = new ArrayList<>();
        for(String app : mApps) {
            ApplicationInfo info = mDeviceStateResolver.getApplicationInfo(app);
            if(info != null) {
                result.add(info);
            }
        }
        onApps(result);
    }
}
