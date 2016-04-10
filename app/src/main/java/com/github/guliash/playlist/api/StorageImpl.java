package com.github.guliash.playlist.api;

import com.github.guliash.playlist.structures.Singer;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by gulash on 10.04.16.
 */
public class StorageImpl implements Storage {

    private List<Singer> mSingers;
    private PlaylistApi mApi;

    public StorageImpl(PlaylistApi api) {
        mApi = api;
    }

    @Override
    public Observable<List<Singer>> getSingers() {
        if(mSingers != null) {
            return Observable.just(mSingers);
        } else {
            return mApi.getSingers().map(new Func1<List<Singer>, List<Singer>>() {
                @Override
                public List<Singer> call(List<Singer> singers) {
                    mSingers = singers;
                    return singers;
                }
            });
        }
    }
}
