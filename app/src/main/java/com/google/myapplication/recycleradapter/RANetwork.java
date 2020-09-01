package com.google.myapplication.recycleradapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.myapplication.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RANetwork extends RecyclerView.Adapter<RANetwork.Holder> {
    FragmentActivity fa;
    int type;
    public RANetwork(FragmentActivity fa, int type) {
        this.fa = fa;
        this.type = type;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(fa).inflate(R.layout.network_re_view, parent,false);
        return new Holder(v);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (fa == null){
            Log.i("test","NULL "+type);
        }else {
            Log.i("test","NOT NULL"+type);
        }
        int id;
        position /= 5;
        switch (type){
            case 1:
                id = fa.getResources().getIdentifier("m"+(position+1),"drawable",fa.getPackageName());
                break;
            case 2:
                id = fa.getResources().getIdentifier("q"+(position+1),"drawable",fa.getPackageName());
                break;
            case 3:
                id = fa.getResources().getIdentifier("c"+(position+1),"drawable",fa.getPackageName());
                break;
            case 4:
                id = fa.getResources().getIdentifier("h"+(position+1),"drawable",fa.getPackageName());
                break;
            default:
                return;
               }
        if (id != 0)
        holder.iv.setImageDrawable(fa.getDrawable(id));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView iv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image);
        }
    }

}
