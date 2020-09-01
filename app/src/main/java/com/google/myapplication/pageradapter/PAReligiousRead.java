package com.google.myapplication.pageradapter;

import com.google.myapplication.R;
import com.google.myapplication.fragments.religiousread.FragmentReligiousOutline;
import com.google.myapplication.fragments.religiousread.FragmentReligiousPost;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PAReligiousRead extends FragmentPagerAdapter {
    String org_id;
    String uri_im;
    String name_o;
    public PAReligiousRead(@NonNull FragmentManager fm,String org_id,String uri_im,String name_o) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.org_id = org_id;
        this.name_o = name_o;
        this.uri_im = uri_im;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        if (position == 0)
            f = FragmentReligiousOutline.newInstance(org_id,uri_im,name_o);
        else
            f = new FragmentReligiousPost();
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
