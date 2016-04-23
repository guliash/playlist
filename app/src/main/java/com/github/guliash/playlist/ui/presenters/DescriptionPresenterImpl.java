package com.github.guliash.playlist.ui.presenters;

import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.views.DescriptionView;

import javax.inject.Inject;

/**
 * An implementation of {@link DescriptionPresenter}
 */
public class DescriptionPresenterImpl implements DescriptionPresenter{

    private DescriptionView mView;
    private GetSingerInteractor mGetSingerInteractor;
    private Singer mSinger; //TODO SHOULD IT BE HERE OR SHOULD I CREATE A SEPARATE PRESENTER FOR URL

    @Inject
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
                mSinger = singer;
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

    @Override
    public void urlButtonClicked() {
        if(mSinger != null) {
            mView.goToUrl(mSinger.link);
        }
    }
}
