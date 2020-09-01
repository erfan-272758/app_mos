package com.google.myapplication.backend;

import android.app.Application;

import com.google.myapplication.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/b_mitra.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        FollowReligious.onCreate(this);
    }

}
