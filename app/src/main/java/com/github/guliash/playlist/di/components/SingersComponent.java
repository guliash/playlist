package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.PerActivity;
import com.github.guliash.playlist.di.modules.SingersModule;
import com.github.guliash.playlist.ui.views.DescriptionActivity;
import com.github.guliash.playlist.ui.views.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = SingersModule.class)
public interface SingersComponent {

    void inject(MainActivity activity);

    void inject(DescriptionActivity activity);

}
