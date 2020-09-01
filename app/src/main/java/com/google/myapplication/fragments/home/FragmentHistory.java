package com.google.myapplication.fragments.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.myapplication.R;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.backend.ProgressHandle;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.dataclass.PersonHistory;
import com.google.myapplication.recycleradapter.RAFollow;
import com.google.myapplication.recycleradapter.RAHistory;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentHistory extends Fragment implements ProgressHandle.ChangeState{
    public static int NO_PERSON = 5;
    public static int WITH_PERSON = 6;
    ArrayList<PersonHistory> histories = null;
    Holder holder;
    String per_id;
    public static FragmentHistory newInstance(String per_id){
        FragmentHistory f = new FragmentHistory();
        f.setArguments(f.writeIntoBundle(null,per_id));
        return f;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (histories == null){
            histories = PrepareData.getPersonHistory(per_id,null);
        }
        View view = inflater.inflate(R.layout.recycler_view_layout,container,false);
        holder = new Holder(view);
        holder.rv.setAdapter(holder.adapter);
        holder.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ProgressHandle ph = new ProgressHandle(getContext(),this,holder.pb_top,holder.pb_down,holder.rv);
        holder.rv.setOnTouchListener(ph);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState = writeIntoBundle(histories,per_id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            readFromBundle(savedInstanceState);
    }

    private void readFromBundle(Bundle bundle){
        per_id = bundle.getString("per_id","");
        int size_his = bundle.getInt("size_his",-1);
        for (int i = 0; i < size_his; i++) {
            String per_des = bundle.getString("per_des"+i,"");
            String name_o = bundle.getString("name_o"+i,"");
            String uri_o =bundle.getString("uri_o"+i,"");
            String id_o= bundle.getString("id_o"+i,"");
            String bio_o = bundle.getString("bio_o"+i,"");
            boolean follow = bundle.getBoolean("follow_o"+i,false);
            int follow_num = bundle.getInt("follow_num"+i,0);
            int act_num = bundle.getInt("act_num"+i,0);
            MainData.Organ organ = new MainData.Organ(name_o,uri_o,id_o,follow,bio_o,act_num,follow_num);
            String act_des = bundle.getString("act_des"+i,"");
            int act_id = bundle.getInt("act_id"+i,-1);
            float rate_act = bundle.getFloat("rate_act"+i,0f);
            histories.add(new PersonHistory(per_des,organ,act_des,act_id,rate_act));
        }

    }
    private Bundle writeIntoBundle(@Nullable ArrayList<PersonHistory> histories,String per_id){
        Bundle bundle = new Bundle();
        bundle.putString("per_id",per_id);
        if (histories == null)
            return bundle;
        bundle.putInt("size_his",histories.size());
        for (int i=0;i<histories.size();i++) {
            PersonHistory history = histories.get(i);
            bundle.putString("per_des"+i,history.getPer_des());
            bundle.putString("name_o"+i,history.getOrgan().getName_o());
            bundle.putString("uri_o"+i,history.getOrgan().getUri_o());
            bundle.putString("id_o"+i,history.getOrgan().getId_o());
            bundle.putString("bio_o"+i,history.getOrgan().getBio());
            bundle.putBoolean("follow_o"+i,history.getOrgan().isFollow());
            bundle.putInt("act_num"+i,history.getOrgan().getAct_num());
            bundle.putInt("follow_num"+i,history.getOrgan().getFollow_num());
            bundle.putString("act_des"+i,history.getAct_des());
            bundle.putInt("act_id"+i,history.getAct_id());
            bundle.putFloat("rate_act"+i,history.getRate_act());
        }
        return bundle;
    }

    @Override
    public void update() {
        ArrayList<PersonHistory> his = PrepareData.getPersonHistory(per_id,holder.pb_top);
        histories.clear();
        histories.addAll(his);
        holder.adapter.update(his);
    }

    @Override
    public void extra() {
        ArrayList<PersonHistory> his = PrepareData.extraPersonHistory(per_id,holder.pb_down,holder.adapter.getItemCount());
        histories.addAll(his);
        holder.adapter.extra(his);
    }

    class Holder{
        RecyclerView rv;
        RAHistory adapter;
        ProgressBar pb_top,pb_down;
        Holder(View view){
            rv = view.findViewById(R.id.re_view);
            adapter = new RAHistory(getContext(),histories);
            pb_down = view.findViewById(R.id.pb_down);
            pb_top = view.findViewById(R.id.pb_top);
        }
    }
}
