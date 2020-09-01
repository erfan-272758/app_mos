package com.google.myapplication.pageradapter;

import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.fragments.home.FragmentHome;
import com.google.myapplication.fragments.network.FragmentNetwork;
import com.google.myapplication.fragments.search.FragmentSearch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PAWhole extends FragmentStateAdapter {
    FragmentActivity fa;
    TabLayout tl;
    public PAWhole(FragmentActivity fa, TabLayout tl) {
        super(fa);
        this.fa = fa;
        this.tl = tl;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment f;
        switch (position){
            case 0:
                f = new FragmentHome();
                break;
            case 1:
                f = new FragmentNetwork();
                break;
            default:
                f = new FragmentSearch(tl);
                break;
        }
        return f;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
