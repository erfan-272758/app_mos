package com.google.myapplication.pageradapter;

import com.google.myapplication.fragments.main.FragmentData;
import com.google.myapplication.fragments.main.FragmentTool;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class PAMain extends FragmentStateAdapter {
    ViewPager2 vpMain;
    DrawerLayout dl;
    ArrayList<Fragment> fragments;
    public PAMain(@NonNull FragmentActivity fragmentActivity, ViewPager2 vpMain, DrawerLayout dl) {
        super(fragmentActivity);
        this.vpMain =vpMain;
        this.dl = dl;
        fragments = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment f;
        if (position == 0){
            f = new FragmentData();
        }else {
            f = new FragmentTool(vpMain);
        }
        fragments.add(f);
        return f;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }
}
