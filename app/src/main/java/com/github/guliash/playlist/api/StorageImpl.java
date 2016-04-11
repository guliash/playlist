package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gulash on 10.04.16.
 */
public class StorageImpl implements Storage {

    private List<Singer> mSingers;
    private PlaylistApi mApi;

    public StorageImpl(PlaylistApi api) {
        mApi = api;
    }

    public void getSingers() {
        mApi.getSingers().enqueue(new Callback<List<Singer>>() {
            @Override
            public void onResponse(Call<List<Singer>> call, Response<List<Singer>> response) {
                EventBus.getDefault().post(response.body());
            }

            @Override
            public void onFailure(Call<List<Singer>> call, Throwable t) {
            }
        });
    }
}
