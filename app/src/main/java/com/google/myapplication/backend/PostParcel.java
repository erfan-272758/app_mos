package com.google.myapplication.backend;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.google.myapplication.dataclass.MainData;

import java.util.ArrayList;

public class PostParcel extends View.BaseSavedState {
    ArrayList<MainData> posts;
    public PostParcel() {
        super(Parcel.obtain());
        posts = new ArrayList<>();
    }

    public void addPosts(ArrayList<MainData> newPost){
        posts.addAll(newPost);
    }
    public void updatePosts (ArrayList<MainData> newPost){
        posts.clear();
        posts.addAll(newPost);
    }
    public ArrayList<MainData> getPosts() {
        return posts;
    }
}
