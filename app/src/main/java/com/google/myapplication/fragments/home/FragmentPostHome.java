package com.google.myapplication.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PAPostMyBest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class FragmentPostHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_post,container,false);
        ViewPager vp = v.findViewById(R.id.view_pager_1);
        TabLayout tb = v.findViewById(R.id.tabl);
        PAPostMyBest adapter = new PAPostMyBest(getChildFragmentManager(),getActivity());
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
        return v;
    }
}
