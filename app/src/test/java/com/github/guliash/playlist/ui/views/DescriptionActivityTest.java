package com.github.guliash.playlist.ui.views;

import android.os.Build;

import com.github.guliash.playlist.BuildConfig;
import com.github.guliash.playlist.structures.Singer;
import com.google.gson.Gson;

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

    private static final String singerJSON = "{\"albums\":45,\"cover\":{\"big\":\"" +
            "http://avatars.yandex.net/get-music-content/a0e5b2b6.p.36800/1000x1000\"," +
            "\"small\":\"http://avatars.yandex.net/get-music-content/a0e5b2b6.p.36800/300x300\"}," +
            "\"description\":\"американская альтернативная рок-группа, основанная в 1996 году под " +
            "названием Xero. Существуя с 2000 года под названием Linkin Park, группа два раза " +
            "удостоилась награды «Грэмми». Группа обрела успех благодаря дебютному альбому 2000 " +
            "года под названием Hybrid Theory, проданному тиражом в 27 миллионов экземпляров. " +
            "Следующий студийный альбом, Meteora, повторил, хоть и не превзошёл успех предыдущего, " +
            "лидируя в 2003 году в чарте Billboard 200. В общей сложности альбомы группы разошлись " +
            "общим тиражом около 73 миллионов экземпляров.\",\"genres\":[\"alternative\"]," +
            "\"id\":36800,\"link\":\"http://www.linkinpark.com/main\",\"name\":\"Linkin Park\"," +
            "\"tracks\":309}";

    private Singer singer = new Gson().fromJson(singerJSON, Singer.class);


    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(DescriptionActivity.class);
    }

    @Test
    public void singerDataCorrectlyShown() {
        activity.setSinger(singer);
        assertTrue("Name view contains correct name", singer.name.equals(activity.name.getText()));
    }
}
