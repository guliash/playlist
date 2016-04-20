package com.github.guliash.playlist.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by gulash on 20.04.16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}