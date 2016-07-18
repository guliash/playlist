package com.github.guliash.playlist.ui.views;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.utils.DeviceStateResolver;

public class MainActivity extends BaseActivity implements SingersListFragment.Callbacks, FragmentManager.OnBackStackChangedListener {

    static final String FRAGMENT_TAG = "fragment_tag";

    private static final String FRAGMENT_SHOWN_EXTRA = "fragment_shown";

    private static final int LIST_FRAGMENT = 0;
    private static final int DESC_FRAGMENT = 1;
    private static final int ABOUT_FRAGMENT = 2;
    private static final int FEEDBACK_FRAGMENT = 3;

    private int mCurrentFragment = 0;

    private DeviceStateResolver mDeviceStateResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        if(savedInstanceState == null) {
            showListFragment();
        } else {
            mCurrentFragment = savedInstanceState.getInt(FRAGMENT_SHOWN_EXTRA);
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        mDeviceStateResolver = getAppComponent().deviceStateResolver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mDeviceStateResolver.areHeadphonesPluggedIn()) {
            showMusicNotification();
        }
    }

    private void showMusicNotification() {
        Intent yaMusicIntent = mDeviceStateResolver.getLaunchIntentIfPackageInstalled(
                DeviceStateResolver.YANDEX_MUSIC_PACKAGE);
        Intent yaRadioIntent = mDeviceStateResolver.getLaunchIntentIfPackageInstalled(
                DeviceStateResolver.YANDEX_RADIO_PACKAGE);

        if(yaMusicIntent == null && yaRadioIntent == null) {
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(getString(R.string.notification_content_title))
                .setContentText(getString(R.string.notification_content_text));

        if(yaMusicIntent != null) {
            PendingIntent pi = PendingIntent.getActivity(this, 0, yaMusicIntent, 0);
            NotificationCompat.Action action = new NotificationCompat.Action(0,
                    getString(R.string.yandex_music), pi);
            builder.addAction(action);
        }
        if(yaRadioIntent != null) {
            PendingIntent pi = PendingIntent.getActivity(this, 0, yaRadioIntent, 0);
            NotificationCompat.Action action = new NotificationCompat.Action(0,
                    getString(R.string.yandex_radio), pi);
            builder.addAction(action);
        }

        builder.setContentIntent(PendingIntent.getActivity(this, 0,
                (yaMusicIntent != null ? yaMusicIntent : yaRadioIntent), 0));
        builder.setAutoCancel(true);

        final int notificationId = 1;
        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(notificationId, builder.build());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENT_SHOWN_EXTRA, mCurrentFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_about:
                showAboutFragment();
                break;
            case R.id.action_feedback:
                showFeedbackFragment();
                break;
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                break;
        }
        return true;
    }

    @Override
    public void onSingerChosen(Singer singer) {
        showDescFragment(singer.id);
    }

    void showAboutFragment() {
        mCurrentFragment = ABOUT_FRAGMENT;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, new AboutFragment(), FRAGMENT_TAG).addToBackStack(null).commit();
        invalidateOptionsMenu();
    }

    void showFeedbackFragment() {
        mCurrentFragment = FEEDBACK_FRAGMENT;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, new FeedbackFragment(), FRAGMENT_TAG).addToBackStack(null).commit();
        invalidateOptionsMenu();
    }

    void showListFragment() {
        mCurrentFragment = LIST_FRAGMENT;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_fragment, new SingersListFragment(), FRAGMENT_TAG).commit();
        invalidateOptionsMenu();
    }

    void showDescFragment(int singerId) {
        mCurrentFragment = DESC_FRAGMENT;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, SingerDescFragment.newInstance(singerId), FRAGMENT_TAG)
                .addToBackStack(null).commit();
        invalidateOptionsMenu();
    }

    @Override
    public void onBackStackChanged() {
        boolean hasEntries = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasEntries);
    }
}
