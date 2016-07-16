package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new
            ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setupActivity() {
        PlaylistApplication application = (PlaylistApplication) InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext().getApplicationContext();
        application.setAppComponent(DaggerTestAppComponent.builder().testAppModule(
                new TestAppModule(application)).build());

    }

    @Test
    public void clickOnCardOpensDescription() {

        mActivityRule.launchActivity(new Intent());

        onView(withId(R.id.singers)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        Singer singer = Constants.getSinger(Constants.SINGER_FIRST_ID);

        onView(withId(R.id.info)).check(ViewAssertions.matches(hasDescendant(withText(singer.name))));
    }
}
