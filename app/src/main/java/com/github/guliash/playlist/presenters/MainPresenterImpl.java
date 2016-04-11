package com.github.guliash.playlist.presenters;

import android.os.Bundle;
import android.text.TextUtils;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gulash on 07.04.16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private String mFilter;

    @Override
    public void onCreate(MainView view, Bundle bundle) {
        mView = view;
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        mView.showProgress();
        PlaylistApplication.getStorage().getSingers();
    }

    @Override
    public void saveState(Bundle bundle) {

    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        mView = null;
    }

    @Override
    public void onSingerClicked(Singer singer) {
        mView.navigateToDescription(singer);
    }

    @Override
    public void onSingersSearch(final String query) {
        mFilter = query;
        mView.showProgress();
        PlaylistApplication.getStorage().getSingers();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSingers(List<Singer> singers) {
        mView.setSingers(applyFilter(singers));
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
