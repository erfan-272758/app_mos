package com.google.myapplication.fragments.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.R;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.backend.ProgressHandle;
import com.google.myapplication.backend.ReadWritePersonInfo;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.recycleradapter.RASuggest;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentData extends Fragment implements View.OnClickListener, ProgressHandle.ChangeState, SearchView.OnQueryTextListener, View.OnTouchListener {
    Holder holder;
    Animation anim_out;
    float oldY;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_data,container,false);
        //Holder
        holder = new Holder(view);
        init();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        //Anim
        anim_out = AnimationUtils.loadAnimation(getContext(),R.anim.anim_out);

        //Recycler View
        holder.adapter.update(PrepareData.getNewSuggest());
        holder.rv.setAdapter(holder.adapter);
        holder.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        holder.rv.setTag(holder);
        ProgressHandle ph = new ProgressHandle(getContext(),this,holder.pb_top,holder.pb_down,holder.rv);
        holder.rv.setOnTouchListener(ph);
        //Search View
        holder.sv.setOnQueryTextListener(this);
        //Button
        holder.btn_more.setOnTouchListener(this);
        holder.btn_more.setOnClickListener(this);
        holder.btn_menu.setOnClickListener(this);
        //PersonInfo
        holder.tv_name.setText(ReadWritePersonInfo.pi.getName());
        holder.tv_id.setText(ReadWritePersonInfo.pi.getShowId());
        holder.iv_p.setImageBitmap(ReadWritePersonInfo.pi.getImage());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_more:
                ((Design.MainAct)(getActivity())).requestViewPager(1);
                break;
            case R.id.btn_menu:
                ((Design.MainAct)(getActivity())).requestDraw();
                break;
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()&MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                oldY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (oldY != 0){
                    Log.i("onTouchL", (oldY - motionEvent.getY()) + "");
                    if ((oldY - motionEvent.getY()) > 50f)
                        ((Design.MainAct)(getActivity())).requestViewPager(1);
                }
                break;
            case MotionEvent.ACTION_UP:
                oldY = 0;
                break;
        }

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<MainData> suggests = PrepareData.getSearchPerson_Suggest(query);
        holder.adapter.update(suggests);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<MainData> suggests;
        if (newText.isEmpty()){
            suggests = PrepareData.getNewSuggest();
        }else {
            suggests = PrepareData.getSearchPerson_Suggest(newText);
        }
        holder.adapter.update(suggests);
        return false;
    }

    @Override
    public void update() {
        holder.adapter.update(PrepareData.getNewSuggest(holder.pb_top));
    }

    @Override
    public void extra() {
        ArrayList<MainData> suggests = PrepareData.getExtraSuggest(holder.pb_down);
        holder.adapter.addSuggests(suggests);
    }

    class Holder{
        RecyclerView rv;
        SearchView sv;
        RASuggest adapter;
        ProgressBar pb_top,pb_down;
        ImageView iv_p;
        TextView tv_name,tv_id;
        MaterialButton btn_more,btn_menu;
        Holder(View view){
            rv = view.findViewById(R.id.re_view);
            sv = view.findViewById(R.id.search);
            adapter = new RASuggest(getActivity());
            pb_top = view.findViewById(R.id.pb_top);
            pb_down = view.findViewById(R.id.pb_down);
            iv_p = view.findViewById(R.id.image_p);
            tv_id = view.findViewById(R.id.text_id);
            tv_name = view.findViewById(R.id.text_name);
            btn_more = view.findViewById(R.id.btn_more);
            btn_menu = view.findViewById(R.id.btn_menu);

        }
    }
    public ArrayList<MainData> getSuggests(){
        return holder.adapter.getSuggests();
    }
    @Override
    public void onResume() {
        holder.adapter.update(PrepareData.getNewSuggest());
        super.onResume();
    }
}
