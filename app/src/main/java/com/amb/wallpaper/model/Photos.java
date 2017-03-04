package com.amb.wallpaper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abarsode on 3/4/17.
 */

public class Photos {

    public List<Photo> getPhotoList() {
        return mPhotoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        mPhotoList = photoList;
    }

    @SerializedName("photo")
    List<Photo> mPhotoList;
}
