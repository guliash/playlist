package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.PerActivity;
import com.github.guliash.playlist.di.modules.SingersModule;
import com.github.guliash.playlist.ui.views.MainActivity;
import com.github.guliash.playlist.ui.views.SingerDescFragment;
import com.github.guliash.playlist.ui.views.SingersListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = SingersModule.class)
public interface SingersComponent {

    void inject(MainActivity activity);

    void inject(SingersListFragment fragment);

    void inject(SingerDescFragment fragment);

}
