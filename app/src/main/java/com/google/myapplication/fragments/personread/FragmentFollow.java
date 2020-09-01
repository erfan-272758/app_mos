package com.google.myapplication.fragments.personread;

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
import com.google.myapplication.recycleradapter.RAFollow;
import com.google.myapplication.recycleradapter.RANetwork;
import com.google.myapplication.recycleradapter.RASearchResult;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentFollow extends Fragment implements ProgressHandle.ChangeState{
    String per_id;
    ArrayList<MainData.Organ> organs = null;
    Holder holder;
    public static FragmentFollow newInstance(String per_id){
        FragmentFollow f = new FragmentFollow();
        f.setArguments(f.writeIntoBundle(null,per_id));
        return f;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (organs == null){
            organs = PrepareData.getOrganFollowing(per_id,null);
        }
        View view = inflater.inflate(R.layout.recycler_view_layout,container,false);
        holder = new Holder(view);
        holder.rv.setAdapter(holder.adapter);
        holder.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        ProgressHandle ph = new ProgressHandle(getContext(),this,holder.pb_top,holder.pb_down,holder.rv);
        holder.rv.setOnTouchListener(ph);
        return view;
    }

    private void readFromBundle(Bundle bundle){
        per_id = bundle.getString("per_id");
        int size_org = bundle.getInt("size_org",-1);
        for (int i = 0; i < size_org; i++) {
            String name_o = bundle.getString("name_o"+i,"");
            String uri_o =bundle.getString("uri_o"+i,"");
            String id_o= bundle.getString("id_o"+i,"");
            String bio_o = bundle.getString("bio_o"+i,"");
            boolean follow = bundle.getBoolean("follow"+i,false);
            int follow_num = bundle.getInt("follow_num"+i,0);
            int act_num = bundle.getInt("act_num"+i,0);
            MainData.Organ organ = new MainData.Organ(name_o,uri_o,id_o,follow,bio_o,act_num,follow_num);
            organs.add(organ);
        }
    }

    private Bundle writeIntoBundle(ArrayList<MainData.Organ> organs,String per_id){
        Bundle bundle = new Bundle();
        bundle.putString("per_id",per_id);
        if (organs == null)
            return bundle;
        bundle.putInt("size_org",organs.size());
        for (int i = 0; i < organs.size(); i++) {
            MainData.Organ organ = organs.get(i);
            bundle.putString("name_o"+i,organ.getName_o());
            bundle.putString("uri_o"+i,organ.getUri_o());
            bundle.putString("id_o"+i,organ.getId_o());
            bundle.putString("bio_o"+i,organ.getBio());
            bundle.putBoolean("follow_o"+i,organ.isFollow());
            bundle.putInt("act_num"+i,organ.getAct_num());
            bundle.putInt("follow_num",organ.getFollow_num());
        }
        return bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            readFromBundle(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState = writeIntoBundle(organs,per_id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void update() {
        ArrayList<MainData.Organ> ors = PrepareData.getOrganFollowing(per_id,holder.pb_top);
        organs.clear();
        organs.addAll(ors);
        holder.adapter.update(organs);
    }

    @Override
    public void extra() {
        ArrayList<MainData.Organ> ors = PrepareData.extraOrganFollowing(per_id,holder.pb_down,holder.adapter.getItemCount());
        organs.addAll(ors);
        holder.adapter.extra(ors);
    }

    class Holder{
        RecyclerView rv;
        RAFollow adapter;
        ProgressBar pb_top,pb_down;
        Holder(View view){
            rv = view.findViewById(R.id.re_view);
            adapter = new RAFollow(getContext(),organs);
            pb_down = view.findViewById(R.id.pb_down);
            pb_top = view.findViewById(R.id.pb_top);
        }
    }
}
