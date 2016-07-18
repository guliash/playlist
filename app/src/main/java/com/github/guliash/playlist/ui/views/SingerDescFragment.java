package com.github.guliash.playlist.ui.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.guliash.playlist.R;
import com.github.guliash.playlist.di.components.DaggerSingersComponent;
import com.github.guliash.playlist.structures.Singer;
import com.github.guliash.playlist.ui.presenters.DescriptionViewPresenterImpl;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingerDescFragment extends BaseFragment implements DescriptionView {

    @Inject
    DescriptionViewPresenterImpl mPresenter;

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.genres)
    TextView genres;
    @Bind(R.id.tracks)
    TextView tracks;
    @Bind(R.id.albums)
    TextView albums;
    @Bind(R.id.desc)
    TextView desc;
    @Bind(R.id.progressBar)
    ProgressBar progress;
    @Bind(R.id.info)
    LinearLayout info;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private int mSingerId;

    public static final String SINGER_ID_EXTRA = "singer_id";

    public static SingerDescFragment newInstance(int singerId) {
        Bundle bundle = new Bundle();

        bundle.putInt(SINGER_ID_EXTRA, singerId);

        SingerDescFragment fr = new SingerDescFragment();
        fr.setArguments(bundle);

        return fr;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = savedInstanceState == null ? getArguments() : savedInstanceState;

        DaggerSingersComponent.builder().appComponent(getAppComponent()).build().inject(this);

        setHasOptionsMenu(true);

        mSingerId = bundle.getInt(SINGER_ID_EXTRA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desc, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onViewAttach(this);
        mPresenter.getSinger(mSingerId);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onViewDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SINGER_ID_EXTRA, mSingerId);
    }

    @OnClick(R.id.fab)
    public void urlFabClicked() {
        mPresenter.urlButtonClicked();
    }

    @Override
    public void setSinger(Singer singer) {
        name.setText(singer.name);
        genres.setText(TextUtils.join(", ", singer.genres));
        tracks.setText(String.valueOf(singer.tracks));
        albums.setText(String.valueOf(singer.albums));
        desc.setText(singer.description);
        fab.setVisibility(View.VISIBLE);
        Picasso.with(getContext()).load(singer.cover.small).error(R.drawable.guitar).into(image);
    }

    @Override
    public void onError(Throwable e) {
        Snackbar snackbar = Snackbar.make(name, e.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
        info.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.INVISIBLE);
        info.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToUrl(@Nullable String url) {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
