package com.github.guliash.playlist.presenters;

import android.text.TextUtils;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractorImpl;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gulash on 07.04.16.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mView;
    private String mFilter;
    private GetSingersInteractor mGetSingersInteractor;

    public MainPresenterImpl() {
        mGetSingersInteractor = new GetSingersInteractorImpl(PlaylistApplication.getStorage(),
                PlaylistApplication.getJobExecutor(), PlaylistApplication.getUIExecutor());
    }

    @Override
    public void onViewAttach(MainView view) {
        mView = view;
        mView.showProgress();
        mGetSingersInteractor.execute(mCallbacks);
    }

    @Override
    public void onViewDetach() {
        mView = null;
    }

    GetSingersInteractor.Callbacks mCallbacks = new GetSingersInteractor.Callbacks() {
        @Override
        public void onSingers(List<Singer> singers) {
            if(mView != null) {
                mView.hideProgress();
                mView.setSingers(applyFilter(singers));
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
        mView.showProgress();
        mGetSingersInteractor.execute(mCallbacks);
    }

    @Override
    public void onSingersRefresh() {
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
