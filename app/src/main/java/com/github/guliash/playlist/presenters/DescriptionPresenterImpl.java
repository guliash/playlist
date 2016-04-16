package com.github.guliash.playlist.presenters;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.DescriptionView;

/**
 * Created by gulash on 07.04.16.
 */
public class DescriptionPresenterImpl implements DescriptionPresenter{

    private DescriptionView mView;
    private Singer mSinger;
    private GetSingerInteractor mGetSingerInteractor;

    public DescriptionPresenterImpl() {
        mGetSingerInteractor = new GetSingerInteractorImpl(PlaylistApplication.getStorage(),
                PlaylistApplication.getJobExecutor(), PlaylistApplication.getUIExecutor());
    }

    @Override
    public void onViewAttach(DescriptionView view) {
        mView = view;
    }

    @Override
    public void onViewDetach() {
        mView = null;
    }

    private GetSingerInteractor.Callbacks mCallbacks = new GetSingerInteractor.Callbacks() {
        @Override
        public void onSinger(Singer singer) {
            if(mView != null) {
                mView.setSinger(singer);
            }
        }

        @Override
        public void onError(Throwable e) {
            if(mView != null) {
                mView.onError(e);
            }
        }
    };

    @Override
    public void getSinger(int id) {
        mGetSingerInteractor.execute(mCallbacks, id);
    }
}
