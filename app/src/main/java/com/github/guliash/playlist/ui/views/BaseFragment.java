package com.github.guliash.playlist.ui.views;

import android.support.v4.app.Fragment;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.di.components.AppComponent;

public class BaseFragment extends Fragment {

    protected AppComponent getAppComponent() {
        return ((PlaylistApplication)getActivity().getApplication()).getAppComponent();
    }

}
