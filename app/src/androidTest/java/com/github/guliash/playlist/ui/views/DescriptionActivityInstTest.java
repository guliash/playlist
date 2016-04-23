package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.R;
import com.github.guliash.playlist.di.components.DaggerTestAppComponent;
import com.github.guliash.playlist.di.modules.TestAppModule;
import com.github.guliash.playlist.structures.Constants;
import com.github.guliash.playlist.structures.Singer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DescriptionActivityInstTest {

    @Rule
    public ActivityTestRule<DescriptionActivity> mActivityRule = new
            ActivityTestRule<>(DescriptionActivity.class, true, false);

    @Before
    public void setupActivity() {
        PlaylistApplication application = (PlaylistApplication) InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext().getApplicationContext();
        application.setAppComponent(DaggerTestAppComponent.builder().testAppModule(
                new TestAppModule(application)).build());
    }

    /**
     * Tests that a singer's data is correctly shown
     * @throws Throwable
     */
    @Test
    public void validateSingerData() throws Throwable {
        Intent intent = new Intent();
        intent.putExtra(DescriptionView.SINGER_ID_EXTRA, Constants.SINGER_FIRST_ID);
        mActivityRule.launchActivity(intent);

        Singer singer = Constants.getSinger(Constants.SINGER_FIRST_ID);
        onView(withId(R.id.desc)).check(matches(withText(singer.description)));
        onView(withId(R.id.name)).check(matches(withText(singer.name)));
        onView(withId(R.id.albums)).check(matches(withText(singer.albums.toString())));
        onView(withId(R.id.tracks)).check(matches(withText(singer.tracks.toString())));
    }


    /**
     * Tests that an intent for browser successfully sent
     * @throws Throwable
     */
    @Test
    public void validateViewIntentSent() throws Throwable {
        Intents.init();

        Intent intent = new Intent();
        intent.putExtra(DescriptionView.SINGER_ID_EXTRA, Constants.SINGER_SECOND_ID);
        mActivityRule.launchActivity(intent);

        Singer singer = Constants.getSinger(Constants.SINGER_SECOND_ID);
        onView(ViewMatchers.withId(R.id.fab)).perform(click());
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(singer.link)));

        Intents.release();
    }
}
