package com.github.guliash.playlist.presenters;

import android.os.Bundle;

/**
 * Created by gulash on 07.04.16.
 */
public interface BasePresenter {

    void onStart();

    void saveState(Bundle bundle);

    void onDestroy();

}
