package com.google.myapplication.pageradapter;

import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.fragments.FragmentSuggestList;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PASuggestList extends FragmentStateAdapter {
    MainData md;
    ArrayList<FragmentSuggestList> fragments;
    public PASuggestList(FragmentActivity fa, MainData md) {
        super(fa);
        this.md = md;
        fragments = new ArrayList<>();
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        FragmentSuggestList fragment = FragmentSuggestList.newInstance(md.getDescribe_p().get(position),md.getOrgans().get(position).getName_o(),
                md.getOrgans().get(position).getUri_o(),md.getPosts().get(position).getDes_act(),md.getPosts().get(position).isSeen(),md.getPosts().get(position).getId_act(),
                md.getPosts().get(position).getAct_rate());
        fragments.add(fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return md.getPosts().size();
    }

}
