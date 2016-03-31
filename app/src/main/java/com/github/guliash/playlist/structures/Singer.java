package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

public class Singer {

    @SerializedName("id")
    private Integer mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("genres")
    private String[] mGenres;

    @SerializedName("tracks")
    private Integer mTracks;

    @SerializedName("albums")
    private Integer mAlbums;

    @SerializedName("link")
    private String mLink;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("cover")
    private Cover mCover;

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String[] getGenres() {
        return mGenres;
    }

    public void setGenres(String[] genres) {
        mGenres = genres;
    }

    public Integer getTracks() {
        return mTracks;
    }

    public void setTracks(Integer tracks) {
        mTracks = tracks;
    }

    public Integer getAlbums() {
        return mAlbums;
    }

    public void setAlbums(Integer albums) {
        mAlbums = albums;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Cover getCover() {
        return mCover;
    }

    public void setCover(Cover cover) {
        mCover = cover;
    }
}
