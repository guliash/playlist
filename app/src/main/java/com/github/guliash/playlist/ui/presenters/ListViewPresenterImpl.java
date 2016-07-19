package com.github.guliash.playlist.ui.presenters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;

import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.ListView;
import com.github.guliash.playlist.utils.DeviceStateResolver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * An implementation of {@link ListViewPresenter}
 */
public class ListViewPresenterImpl implements ListViewPresenter {

    private ListView mView;
    private String mFilter;
    private GetSingersInteractor mGetSingersInteractor;
    private DeviceStateResolver mDeviceStateResolver;
    private Context mContext;

    private static final String[] APPS = new String[] {"ru.yandex.radio", "ru.yandex.music"};

    private BroadcastReceiver mHeadphonesBroadcats = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int microState = intent.getIntExtra("state", 0);
            if(microState == 0) {
                mView.hideApps();
            } else {
                getApps();
            }
        }
    };

    @Inject
    public ListViewPresenterImpl(GetSingersInteractor getSingersInteractor,
                                 DeviceStateResolver deviceStateResolver, Context context) {
        mGetSingersInteractor = getSingersInteractor;
        mDeviceStateResolver = deviceStateResolver;
        mContext = context;
    }

    @Override
    public void onViewAttach(ListView view) {
        mView = view;
        mView.showProgress();
        mContext.registerReceiver(mHeadphonesBroadcats, new IntentFilter(Intent.ACTION_HEADSET_PLUG));

        getSingers();
        if(mDeviceStateResolver.areHeadphonesPluggedIn()) {
            getApps();
        }
    }

    @Override
    public void onViewDetach() {
        mView = null;
        mContext.unregisterReceiver(mHeadphonesBroadcats);

    }

    GetSingersInteractor.Callbacks mCallbacks = new GetSingersInteractor.Callbacks() {
        @Override
        public void onSingers(List<Singer> singers) {
            if(mView != null) {
                mView.setSingers(applyFilter(singers));
                mView.hideProgress();
            }
        }

        @Override
        public void onError(Throwable e) {
            if(mView != null) {
                mView.hideProgress();
                mView.onSingersError(e);
            }
        }
    };

    @Override
    public void onSingerSelected(Singer singer) {
        mView.navigateToDescription(singer);
    }

    @Override
    public void onSingersSearch(final String query) {
        mFilter = query;
        getSingers();
    }

    @Override
    public void onSingersRefresh() {
        getSingers();
    }

    @Override
    public void onAppSelected(ApplicationInfo app) {
        Intent launchIntent = mDeviceStateResolver.getLaunchIntentIfPackageInstalled(app.packageName);
        if(launchIntent != null) {
            mContext.startActivity(launchIntent);
        }

    }

    private void getSingers() {
        mGetSingersInteractor.execute(mCallbacks);
    }

    private void getApps() {
        List<ApplicationInfo> apps = new ArrayList<>();
        for(String app : APPS) {
            ApplicationInfo info = mDeviceStateResolver.getApplicationInfo(app);
            if(info != null) {
                apps.add(info);
            }
        }
        if(apps.size() != 0) {
            mView.setApps(apps);
            mView.previewApps();
        }
    }

    private List<Singer> applyFilter(List<Singer> singers) {
        if (TextUtils.isEmpty(mFilter)) {
            return singers;
        }
        List<Singer> result = new ArrayList<>();
        for(Singer singer : singers) {
            if(singer.name.toLowerCase().startsWith(mFilter)) {
                result.add(singer);
            }
        }
        return result;
    }

}
