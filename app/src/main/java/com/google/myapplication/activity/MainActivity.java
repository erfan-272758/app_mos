package com.google.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import kotlin.jvm.functions.Function3;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.fragments.main.FragmentData;
import com.google.myapplication.pageradapter.PAMain;
import com.google.myapplication.ui.Design;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.widget.AccountHeaderView;
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener, NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, View.OnTouchListener, MainData.Data , Design.MainAct {
    ViewPager2 vpMain;
    DrawerLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate_layout);
        makeDrawer();
        init();
    }
    private void makeDrawer() {
        dl = findViewById(R.id.draw_layout);
        MaterialDrawerSliderView sliderView = findViewById(R.id.slider);
        String[] strs = new String[]{"تغییر پروفایل","رویداد های جدید","ورود به عنوان مسئول مجموعه","درباره ی ما"};
        int[] ids = new int[]{R.drawable.edit,R.drawable.clock,R.drawable.sign_in,R.drawable.about_us};
        View v = LayoutInflater.from(this).inflate(R.layout.header_layout,sliderView,false);
        sliderView.setHeaderView(v);
        v = sliderView.getHeaderView();
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = 170;
        v.setLayoutParams(lp);
        sliderView.setHeaderView(v);

        for (int i = 0; i < 4; i++) {
            PrimaryDrawerItem item = new PrimaryDrawerItem();
            item.setName(new StringHolder(strs[i]));
            item.setIdentifier(i);
            ImageHolder im1 = new ImageIcon();
            im1.setIcon$materialdrawer(getResources().getDrawable(ids[i]));
            item.setIcon(im1);
            sliderView.getItemAdapter().add(item);
        }
        sliderView.setOnDrawerItemClickListener(new Function3<View, IDrawerItem<?>, Integer, Boolean>() {
            @Override
            public Boolean invoke(View view, IDrawerItem<?> iDrawerItem, Integer integer) {
                switch ((int) iDrawerItem.getIdentifier()){
                    case 0:
                        Intent intent0= new Intent(MainActivity.this,DrawerActivity.class);
                        intent0.putExtra("type",DrawerActivity.EDIT_PROF_FRAGMENT);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1= new Intent(MainActivity.this,DrawerActivity.class);
                        intent1.putExtra("type",DrawerActivity.ALERT_FRAGMENT);
                        startActivity(intent1);
                        break;
                    case 2:
                        break;
                    case 3:
                        Dialog d = new Dialog(MainActivity.this,R.style.DialogStyle);
                        d.setContentView(R.layout.about_us_dialog);
                        d.show();
                        break;
                }
                return true;
            }
        });
    }
    private void init() {
        vpMain = findViewById(R.id.view_pager);
        vpMain.setUserInputEnabled(false);
        PAMain adapter = new PAMain(this,vpMain,dl);
        vpMain.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(Gravity.RIGHT)){
            dl.closeDrawer(Gravity.RIGHT);
        }else if (vpMain.getCurrentItem() == 1){
            vpMain.setCurrentItem(0,true);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Log.i("aaa","se");
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("aaa","se1");
        return false;
    }

    @Override
    public void onClick(View view) {
        Log.i("aaa","se2");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("aaa",motionEvent.getAction()+"");
        return false;
    }

    @Override
    protected void onResume() {
        if (dl.isDrawerOpen(Gravity.RIGHT)){
            dl.closeDrawer(Gravity.RIGHT);
        }
        super.onResume();
    }

    @Override
    public ArrayList<MainData> getMainData() {
        PAMain pm = (PAMain) vpMain.getAdapter();
        return ((FragmentData)(pm.getFragments().get(0))).getSuggests();
      }

    @Override
    public void requestDraw() {
        if (!dl.isDrawerOpen(Gravity.RIGHT))
            dl.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void requestViewPager(int pos) {
        vpMain.setCurrentItem(pos,true);
    }
}
