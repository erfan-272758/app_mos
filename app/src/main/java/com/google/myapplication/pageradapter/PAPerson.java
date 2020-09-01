package com.google.myapplication.pageradapter;

import com.google.myapplication.dataclass.PersonHistory;
import com.google.myapplication.fragments.home.FragmentHistory;
import com.google.myapplication.fragments.personread.FragmentFollow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PAPerson extends FragmentPagerAdapter {
    String per_id;
    public PAPerson(@NonNull FragmentManager fm,String per_id) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.per_id = per_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment f;
        if (position == 0){
            f = FragmentHistory.newInstance(per_id);
        }if (position == 1){
            f = FragmentFollow.newInstance(per_id);
        }else {
            f = new Fragment();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
