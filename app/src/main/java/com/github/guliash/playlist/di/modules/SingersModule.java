package com.github.guliash.playlist.di.modules;

import com.github.guliash.playlist.di.PerFragment;
import com.github.guliash.playlist.interactors.GetAppsInteractor;
import com.github.guliash.playlist.interactors.GetAppsInteractorImpl;
import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SingersModule {

    @Provides
    @PerFragment
    GetSingerInteractor provideGetSingerInteractor(GetSingerInteractorImpl getSingerInteractor) {
        return getSingerInteractor;
    }

    @Provides
    @PerFragment
    GetSingersInteractor provideGetSingersInteractor(GetSingersInteractorImpl getSingersInteractor) {
        return getSingersInteractor;
    }

    @Provides
    @PerFragment
    GetAppsInteractor provideGetAppsInteractor(GetAppsInteractorImpl getAppsInteractor) {
        return getAppsInteractor;
    }
}
