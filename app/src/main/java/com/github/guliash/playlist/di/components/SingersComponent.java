package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.PerFragment;
import com.github.guliash.playlist.di.modules.SingersModule;
import com.github.guliash.playlist.ui.views.SingersFragment;
import com.github.guliash.playlist.ui.views.SingerDescFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = SingersModule.class)
public interface SingersComponent {

    void inject(SingersFragment fragment);

    void inject(SingerDescFragment fragment);

}
