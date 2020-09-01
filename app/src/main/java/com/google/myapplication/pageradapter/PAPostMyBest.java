package com.google.myapplication.pageradapter;

import com.google.myapplication.fragments.home.FragmentPostMy;
import com.google.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PAPostMyBest extends FragmentPagerAdapter {
    FragmentActivity fa;
    public PAPostMyBest(@NonNull FragmentManager fm, FragmentActivity fragmentActivity) {
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fa = fragmentActivity;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
            if (position==0){
                return new FragmentPostMy();
            }
            if (position == 1){
                return new Fragment(R.layout.recycler_view_layout);
            }
            return null;
        }
    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "فعالیت های من" : "برترین فعالیت ها";
    }
}
