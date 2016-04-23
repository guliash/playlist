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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class DescriptionActivityInstTest {

    private PlaylistApplication application;

    @Rule
    public ActivityTestRule<DescriptionActivity> mActivityRule = new
            ActivityTestRule<>(DescriptionActivity.class);

    @Before
    public void setupActivity() {
        application = (PlaylistApplication)InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext().getApplicationContext();
        application.setAppComponent(DaggerTestAppComponent.builder().testAppModule(
                new TestAppModule(application)).build());
        Intent intent = new Intent();
        intent.putExtra(DescriptionView.SINGER_ID_EXTRA, Constants.SINGER_ID);
        mActivityRule.launchActivity(intent);
        Intents.init();
    }

    @Test
    public void validateViewIntentSent() throws Throwable {
        onView(ViewMatchers.withId(R.id.fab)).perform(click());

        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(application.getAppComponent()
                .storage().getSinger(Constants.SINGER_ID).link)));
    }
}
