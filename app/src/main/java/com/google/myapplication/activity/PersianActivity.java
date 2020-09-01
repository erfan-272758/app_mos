package com.google.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.ui.PlayerView;
import com.google.myapplication.R;
import com.google.myapplication.backend.FollowReligious;

import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PersianActivity extends AppCompatActivity {
    private PlayerView lastVideo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/b_mitra.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStop() {
        super.onStop();
        pauseLastVideo(null);
        FollowReligious.onDestroy(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pauseLastVideo(null);
        FollowReligious.onDestroy(this);
    }
    public void setLastVideo(PlayerView video){
        Log.i("my-video","setData");
        lastVideo = video;
    }
    public void pauseLastVideo(PlayerView video){
        if (lastVideo!=null){
            Log.i("my-video","not null");
            if (!lastVideo.equals(video)){
                lastVideo.getPlayer().stop();
                Log.i("my-video","not equal");
            } else
            Log.i("my-video","equal");
        }else
        Log.i("my-video","null");
    }

}