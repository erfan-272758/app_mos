package com.google.myapplication.pageradapter;

import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.fragments.FragmentPost;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PAShowPosts extends FragmentStateAdapter {
    FragmentActivity fa;
    ArrayList<MainData> mds;
    int type;
    public PAShowPosts(@NonNull FragmentActivity fragmentActivity,int type) {
        super(fragmentActivity);
        fa = fragmentActivity;
        mds = new ArrayList<>();
        this.type = type;
    }
    synchronized public void addMainDataFirst(MainData md){
        if (!mds.contains(md)){
            mds.add(0,md);
            notifyDataSetChanged();
        }
    }
    synchronized public void addMainDataLast(MainData md){
        if (!mds.contains(md)) {
            mds.add(md);
            notifyDataSetChanged();
        }
    }
    synchronized public void addPostFirst(MainData.Post post,MainData md){
        int index = mds.indexOf(md);
        if (index == -1)
            return;
        if (mds.get(index).getPosts().contains(post))
            return;
        mds.get(index).getPosts().add(0,post);
        notifyDataSetChanged();
    }
    synchronized public void addPostLast(MainData.Post post,MainData md){
        int index = mds.indexOf(md);
        if (index == -1)
            return;
        if (mds.get(index).getPosts().contains(post))
            return;
        mds.get(index).getPosts().add(post);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int[] pos = findPos(position);
        return FragmentPost.newInstance(mds.get(pos[0]),pos[1],type);
    }
    private int[] findPos(int pos){
        int[] count = new int[2];
        int sum = 0;
        for (int i = 0; i < mds.size(); i++) {
            MainData md = mds.get(i);
            if (sum + md.getPosts().size() > pos){
                count[0] = i;
                count[1] = pos - sum ;
                return count;
            }else {
                sum +=md.getPosts().size();
            }
        }
        return count;
    }
    @Override
    public int getItemCount() {
        int count = 0;
        for (MainData md:mds) {
            count += md.getPosts().size();
        }
        return count;
    }
}
