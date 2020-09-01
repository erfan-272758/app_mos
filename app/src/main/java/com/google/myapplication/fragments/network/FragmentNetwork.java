package com.google.myapplication.fragments.network;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PANetwork;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentNetwork extends Fragment {

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.network,container,false);
        ViewPager vp = v.findViewById(R.id.view_pager_1);
        PANetwork adapter = new PANetwork(getChildFragmentManager(),getActivity());
        vp.setAdapter(adapter);
        final TabLayout tl = v.findViewById(R.id.tabl);
        int[]iconIds = new int[]{R.drawable.mosque,R.drawable.quran,R.drawable.culcural,R.drawable.heiat};
        Design.setViewPager(vp,adapter,tl,iconIds);
        return v;
    }
}
