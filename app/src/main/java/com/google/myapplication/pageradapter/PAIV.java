package com.google.myapplication.pageradapter;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Environment;

import com.google.myapplication.fragments.FragmentIV;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PAIV extends FragmentStateAdapter {
    FragmentActivity fa;
    ArrayList<String> uris;
    ArrayList<Integer> flags;
    public PAIV(@NonNull FragmentActivity fragmentActivity,ArrayList<String> uris,ArrayList<Integer> flags) {
        super(fragmentActivity);
        fa = fragmentActivity;
        this.flags = flags;
        this.uris = uris;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentIV.newInstance(uris.get(position),flags.get(position));
    }

    @Override
    public int getItemCount() {
        return flags.size() == uris.size()? Math.min(uris.size(), 7) : -1;
    }
}
