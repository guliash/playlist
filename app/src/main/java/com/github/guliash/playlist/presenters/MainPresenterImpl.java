package com.github.guliash.playlist.presenters;

import android.os.Bundle;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by gulash on 07.04.16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private Subscription mSubscription;

    @Override
    public void onCreate(MainView view, Bundle bundle) {
        mView = view;
    }

    @Override
    public void onStart() {
        mView.showProgress();
        mSubscription = PlaylistApplication.getPlaylistApi()
                .getSingers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Singer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Singer> singers) {
                        mView.hideProgress();
                        mView.setSingers(singers);
                    }
                });

    }

    @Override
    public void saveState(Bundle bundle) {

    }


    @Override
    public void onDestroy() {
        mSubscription.unsubscribe();
        mView = null;
    }

    @Override
    public void onSingerClicked(Singer singer) {
        mView.navigateToDescription(singer);
    }
}
