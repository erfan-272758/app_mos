package com.google.myapplication.fragments.main;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PAWhole;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentTool extends Fragment implements View.OnClickListener, View.OnTouchListener {
    ViewPager2 vp2;
    ViewPager2 vpMain;
    float oldY=0;
    public FragmentTool(ViewPager2 vpMain){
        this.vpMain = vpMain;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tool,container,false);

        //Tool Bar & View Pager
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        vp2 = view.findViewById(R.id.view_pager);
        vp2.setUserInputEnabled(false);
        final TabLayout tl = view.findViewById(R.id.tabl);
        PAWhole adapter =new PAWhole(getActivity(),tl);
        int[] iconIds = new int[]{R.drawable.home,R.drawable.internet,R.drawable.search};
        Design.setViewPager2(vp2,adapter,tl,iconIds);
        //Button
        MaterialButton btn_more = view.findViewById(R.id.btn_more);
        btn_more.setOnClickListener(this);
        btn_more.setOnTouchListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_more){
            vpMain.setCurrentItem(0,true);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("onTouch", (motionEvent.getAction() & MotionEvent.ACTION_MASK) + "");
        switch (motionEvent.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                oldY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldY != 0){
                    Log.i("onTouchL", (oldY - motionEvent.getY()) + "");
                    if ((motionEvent.getY() - oldY) > 50f)
                        vpMain.setCurrentItem(0,true);
                }
                break;
            case MotionEvent.ACTION_UP:
                oldY = 0;
                break;
        }
        return false;
    }
}
