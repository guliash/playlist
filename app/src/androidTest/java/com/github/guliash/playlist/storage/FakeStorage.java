package com.github.guliash.playlist.storage;

import com.github.guliash.playlist.api.Storage;
import com.github.guliash.playlist.structures.Constants;
import com.github.guliash.playlist.structures.Singer;

import java.util.List;

public class FakeStorage implements Storage {

    @Override
    public List<Singer> getSingers() throws Throwable {
        return Constants.getSingers();
    }

    @Override
    public Singer getSinger(int id) throws Throwable {
        List<Singer> singers = Constants.getSingers();
        for(Singer singer : singers) {
            if(singer.id.equals(id)) {
                return singer;
            }
        }
        throw new RuntimeException("not found");
    }
}
