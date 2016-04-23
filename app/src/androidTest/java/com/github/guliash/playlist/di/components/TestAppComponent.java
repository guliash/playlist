package com.github.guliash.playlist.di.components;

import com.github.guliash.playlist.di.modules.TestAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {
}
