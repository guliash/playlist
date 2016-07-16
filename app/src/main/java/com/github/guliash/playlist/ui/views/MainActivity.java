package com.github.guliash.playlist.ui.views;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.github.guliash.playlist.R;

public class MainActivity extends BaseActivity {

    private static final String SINGERS_FRAGMENT_TAG = "singers_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_fragment, new SingersFragment(), SINGERS_FRAGMENT_TAG);
            ft.commit();
        }
    }
}
