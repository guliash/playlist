package com.github.guliash.playlist.ui.views;

import android.os.Build;
import android.text.TextUtils;

import com.github.guliash.playlist.BuildConfig;
import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.di.components.DaggerTestAppComponent;
import com.github.guliash.playlist.di.modules.TestAppModule;
import com.github.guliash.playlist.structures.Constants;
import com.github.guliash.playlist.structures.Singer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class DescriptionFragmentTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        PlaylistApplication application = (PlaylistApplication) RuntimeEnvironment.application;
        application.setAppComponent(DaggerTestAppComponent.builder().testAppModule(
                new TestAppModule(application)).build());
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void singerDataCorrectlyShown() {
        Singer singer = Constants.getSinger(Constants.SINGER_FIRST_ID);
        activity.showDescFragment(singer.id);
        SingerDescFragment fragment = (SingerDescFragment)activity.getSupportFragmentManager()
                .findFragmentByTag(MainActivity.FRAGMENT_TAG);
        assertTrue("Name view contains correct text", TextUtils.equals(singer.name, fragment.name
                .getText()));
        assertTrue("Tracks view contains correct text", TextUtils.equals(singer.tracks.toString(),
                fragment.tracks.getText()));
        assertTrue("Albums view contains correct text", TextUtils.equals(singer.albums.toString(),
                fragment.albums.getText()));
        assertTrue("Description view contains correct text", TextUtils.equals(singer.description,
                fragment.desc.getText()));
    }
}
