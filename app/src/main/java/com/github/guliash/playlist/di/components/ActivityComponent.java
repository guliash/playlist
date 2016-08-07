package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.PerActivity;
import com.github.guliash.playlist.ui.views.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

}
