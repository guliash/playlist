package com.github.guliash.playlist.ui.presenters;

import android.text.TextUtils;

import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private String mFilter;
    private GetSingersInteractor mGetSingersInteractor;

    @Inject
    public MainPresenterImpl(GetSingersInteractor getSingersInteractor) {
        mGetSingersInteractor = getSingersInteractor;
    }

    @Override
    public void onViewAttach(MainView view) {
        mView = view;
        mView.showProgress();
        getSingers();
    }

    @Override
    public void onViewDetach() {
        mView = null;
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
    public void onSingerClicked(Singer singer) {
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

    private void getSingers() {
        mGetSingersInteractor.execute(mCallbacks);
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
