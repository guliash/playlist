package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.PerFragment;
import com.github.guliash.playlist.di.modules.SingersModule;
import com.github.guliash.playlist.ui.views.SingerDescFragment;
import com.github.guliash.playlist.ui.views.SingersListFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = SingersModule.class)
public interface SingersComponent {

    void inject(SingersListFragment fragment);

    void inject(SingerDescFragment fragment);

}
