package com.github.guliash.playlist.presenters;

import android.os.Bundle;

import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.views.DescriptionView;

import org.parceler.Parcels;

/**
 * Created by gulash on 07.04.16.
 */
public class DescriptionPresenterImpl implements DescriptionPresenter{

    private DescriptionView mView;
    private Singer mSinger;

    @Override
    public void onCreate(DescriptionView view, Bundle bundle) {
        mView = view;
        mSinger = Parcels.unwrap(bundle.getParcelable(SINGER_EXTRA));
    }

    @Override
    public void onStart() {
        mView.setSinger(mSinger);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putParcelable(SINGER_EXTRA, Parcels.wrap(mSinger));
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
