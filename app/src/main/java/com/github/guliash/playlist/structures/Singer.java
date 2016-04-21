package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

public class Singer {

    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String name;

    @SerializedName("genres")
    public String[] genres;

    @SerializedName("tracks")
    public Integer tracks;

    @SerializedName("albums")
    public Integer albums;

    @SerializedName("link")
    public String link;

    @SerializedName("description")
    public String description;

    @SerializedName("cover")
    public Cover cover;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
