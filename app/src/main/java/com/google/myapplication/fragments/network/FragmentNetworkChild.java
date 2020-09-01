package com.google.myapplication.fragments.network;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.myapplication.R;
import com.google.myapplication.recycleradapter.RANetwork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentNetworkChild extends Fragment {
    int type;
    public void initData(int type) {
        this.type = type;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view_layout,container,false);
        RecyclerView rv = v.findViewById(R.id.re_view);
        RANetwork adapter = new RANetwork(getActivity(),type);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("abc","Un");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("abc","En");
                        break;
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("type",type);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null){
        int t = savedInstanceState.getInt("type",-1);
        if (t != -1)
            type = t;
        }
        super.onViewStateRestored(savedInstanceState);
    }
}
