package com.github.guliash.playlist.structures;

import com.google.gson.annotations.SerializedName;

public class Cover {
    @SerializedName("small")
    private String mSmall;

    @SerializedName("big")
    private String mBig;

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

    public String getBig() {
        return mBig;
    }

    public void setBig(String big) {
        mBig = big;
    }
}
