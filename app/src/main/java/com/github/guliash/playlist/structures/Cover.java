package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Cover {
    @SerializedName("small")
    public String small;

    @SerializedName("big")
    public String big;
    
}
