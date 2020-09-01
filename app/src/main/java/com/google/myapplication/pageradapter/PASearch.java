package com.google.myapplication.pageradapter;

import com.google.myapplication.fragments.search.FragmentSearchList;
import com.google.myapplication.fragments.search.FragmentSearchMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PASearch extends FragmentPagerAdapter {
    public PASearch(@NonNull FragmentManager fm) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        if (position == 0){
            f = new FragmentSearchList();
        }else {
            f = new FragmentSearchMap();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
