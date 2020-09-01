package com.google.myapplication.fragments.search;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.activity.SearchDialog;
import com.google.myapplication.pageradapter.PASearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class FragmentSearch extends Fragment {
    TabLayout tl;

    public FragmentSearch(TabLayout tl) {
        this.tl = tl;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shearch,container,false);
        //Search View
        final SearchView sw = view.findViewById(R.id.search);
        //Tab Layout & View Pager
        ViewPager vp = view.findViewById(R.id.view_pager_1);
        TabLayout tl = view.findViewById(R.id.tl);
        MaterialButton btn_setting = view.findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchDialog sd = new SearchDialog(getContext());
                sd.show();
            }
        });
        vp.setAdapter(new PASearch(getChildFragmentManager()));
        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setIcon(R.drawable.search_list);
        tl.getTabAt(1).setIcon(R.drawable.search_map);
        tl.getTabAt(0).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        tl.getTabAt(1).getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
