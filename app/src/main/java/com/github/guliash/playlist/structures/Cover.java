package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Cover {
    @SerializedName("small")
    public String mSmall;

    @SerializedName("big")
    public String mBig;
    
}
