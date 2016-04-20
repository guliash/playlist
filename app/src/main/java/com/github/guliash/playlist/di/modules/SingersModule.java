package com.github.guliash.playlist.di.modules;

import com.github.guliash.playlist.interactors.GetSingerInteractor;
import com.github.guliash.playlist.interactors.GetSingerInteractorImpl;
import com.github.guliash.playlist.interactors.GetSingersInteractor;
import com.github.guliash.playlist.interactors.GetSingersInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gulash on 20.04.16.
 */
@Module
public class SingersModule {

    @Provides
    GetSingerInteractor provideGetSingerInteractor(GetSingerInteractorImpl getSingerInteractor) {
        return getSingerInteractor;
    }

    @Provides
    GetSingersInteractor provideGetSingersInteractor(GetSingersInteractorImpl getSingersInteractor) {
        return getSingersInteractor;
    }

}
