package com.github.guliash.playlist.ui.views;

import android.os.Build;
import android.text.TextUtils;

import com.github.guliash.playlist.BuildConfig;
import com.github.guliash.playlist.structures.Constants;
import com.github.guliash.playlist.structures.Singer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by gulash on 19.04.16.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class DescriptionActivityTest {

    private DescriptionActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(DescriptionActivity.class);
    }

    @Test
    public void singerDataCorrectlyShown() {
        Singer singer = Constants.getSinger(Constants.SINGER_FIRST_ID);
        activity.setSinger(singer);
        assertTrue("Name view contains correct text", TextUtils.equals(singer.name, activity.name
                .getText()));
        assertTrue("Tracks view contains correct text", TextUtils.equals(singer.tracks.toString(),
                activity.tracks.getText()));
        assertTrue("Albums view contains correct text", TextUtils.equals(singer.albums.toString(),
                activity.albums.getText()));
        assertTrue("Description view contains correct text", TextUtils.equals(singer.description,
                activity.desc.getText()));
    }
}
