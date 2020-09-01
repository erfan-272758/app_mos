package com.google.myapplication.fragments.home;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PAHome;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home,container,false);
        ViewPager vp = v.findViewById(R.id.view_pager_1);
        final TabLayout tl = v.findViewById(R.id.tabl);
        PAHome adapter = new PAHome(getChildFragmentManager(),getActivity());
        int[] iconIds = new int[]{R.drawable.activity,R.drawable.history};
        Design.setViewPager(vp,adapter,tl,iconIds);
        return v;
    }
}
