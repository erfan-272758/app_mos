package com.google.myapplication.pageradapter;

import com.google.myapplication.fragments.network.FragmentNetworkChild;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PANetwork extends FragmentPagerAdapter {
    FragmentActivity fa;
    public PANetwork(@NonNull FragmentManager fm, FragmentActivity fa) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fa = fa;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        FragmentNetworkChild f = new FragmentNetworkChild();
        f.initData(position+1);
        return f;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
