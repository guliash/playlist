package com.github.guliash.playlist.di.modules;

import com.github.guliash.playlist.di.PerActivity;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SingersModule {

    @Provides
    @PerActivity
    GetSingerInteractor provideGetSingerInteractor(GetSingerInteractorImpl getSingerInteractor) {
        return getSingerInteractor;
    }

    @Provides
    @PerActivity
    GetSingersInteractor provideGetSingersInteractor(GetSingersInteractorImpl getSingersInteractor) {
        return getSingersInteractor;
    }

}
