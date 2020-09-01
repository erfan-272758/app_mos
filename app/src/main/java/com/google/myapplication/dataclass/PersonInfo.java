package com.google.myapplication.dataclass;

import android.graphics.Bitmap;

import java.io.Serializable;

public class PersonInfo implements Serializable {
    String name;
    String bio;
    String showId;
    int hiddenId;
    Bitmap image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public int getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(int hiddenId) {
        this.hiddenId = hiddenId;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
