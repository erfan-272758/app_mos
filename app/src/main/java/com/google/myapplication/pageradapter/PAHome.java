package com.google.myapplication.pageradapter;

import android.util.Log;

import com.google.myapplication.fragments.home.FragmentHistory;
import com.google.myapplication.fragments.home.FragmentPostHome;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PAHome extends FragmentPagerAdapter {
    FragmentActivity fa;
    public PAHome(@NonNull FragmentManager fm, FragmentActivity fa) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fa = fa;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        Log.i("my-tab",position+"");
        if (position == 0){
            f = new FragmentPostHome();
        }else {
            f = new FragmentHistory();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
