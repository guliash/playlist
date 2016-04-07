package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Singer {

    @SerializedName("id")
    public Integer mId;

    @SerializedName("name")
    public String mName;

    @SerializedName("genres")
    public String[] mGenres;

    @SerializedName("tracks")
    public Integer mTracks;

    @SerializedName("albums")
    public Integer mAlbums;

    @SerializedName("link")
    public String mLink;

    @SerializedName("description")
    public String mDescription;

    @SerializedName("cover")
    public Cover mCover;

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }
}
