package com.github.guliash.playlist.presenters;

import android.os.Bundle;
import android.util.Log;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gulash on 07.04.16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private CompositeSubscription mSubscription = new CompositeSubscription();

    @Override
    public void onCreate(MainView view, Bundle bundle) {
        mView = view;
    }

    @Override
    public void onStart() {
        mView.showProgress();
        mSubscription.add(PlaylistApplication.getStorage()
                .getSingers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Singer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(null, "ERROR");
                    }

                    @Override
                    public void onNext(List<Singer> singers) {
                        mView.hideProgress();
                        mView.setSingers(singers);
                    }
                }));

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

    @Override
    public void onSingersSearch(final String query) {
        mView.showProgress();
        mSubscription.add(PlaylistApplication.getStorage().getSingers()
                .map(new Func1<List<Singer>, List<Singer>>() {
                    @Override
                    public List<Singer> call(List<Singer> singers) {
                        List<Singer> list = new ArrayList<>();
                        for (Singer singer : singers) {
                            if (singer.name.toLowerCase().startsWith(query.toLowerCase())) {
                                list.add(singer);
                            }
                        }
                        return list;
                    }
                })
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
                }));
    }
}
