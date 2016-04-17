package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.DescriptionView;

/**
 * Created by gulash on 07.04.16.
 */
public class DescriptionPresenterImpl implements DescriptionPresenter{

    private DescriptionView mView;
    private GetSingerInteractor mGetSingerInteractor;

    public DescriptionPresenterImpl(GetSingerInteractor getSingerInteractor) {
        mGetSingerInteractor = getSingerInteractor;
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
                mView.hideLoading();
                mView.setSinger(singer);
            }
        }

        @Override
        public void onError(Throwable e) {
            if(mView != null) {
                mView.hideLoading();
                mView.onError(e);
            }
        }
    };

    @Override
    public void getSinger(int id) {
        mGetSingerInteractor.execute(mCallbacks, id);
        mView.showLoading();
    }
}
