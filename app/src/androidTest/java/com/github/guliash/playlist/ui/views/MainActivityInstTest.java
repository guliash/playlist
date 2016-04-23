package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.guliash.playlist.PlaylistApplication;
import com.github.guliash.playlist.R;
import com.github.guliash.playlist.di.components.DaggerTestAppComponent;
import com.github.guliash.playlist.di.modules.TestAppModule;
import com.github.guliash.playlist.structures.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
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
    public void clickOnCardOpenDescription() {
        mActivityRule.launchActivity(new Intent());

        Intents.init();

        onView(withId(R.id.singers)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(allOf(hasExtra(DescriptionView.SINGER_ID_EXTRA, Constants.SINGER_FIRST_ID)));

        Intents.release();
    }
}
