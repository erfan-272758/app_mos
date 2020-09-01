package com.google.myapplication.recycleradapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.R;
import com.google.myapplication.dataclass.CulturalGroup;
import com.google.myapplication.dataclass.Heiat;
import com.google.myapplication.dataclass.Mosque;
import com.google.myapplication.dataclass.Religious;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RAPrivateInfo extends RecyclerView.Adapter<RAPrivateInfo.Holder> implements View.OnClickListener {
    Context context;
    Religious relig;
    int startRelig,startAddress,phoneIndex;
    ArrayList<String> keys,values;
    public RAPrivateInfo(Context context, Religious relig) {
        this.context = context;
        this.relig = relig;
        keys = new ArrayList<>();
        values = new ArrayList<>();;
        switch (relig.getType()){
            case Religious.MOSQUE:
                startRelig = 4;
                break;
            case Religious.HEIAT:
                startRelig = 5;
                break;
            case Religious.CULTURAL:
            case Religious.QURAN:
                startRelig = 4 + ((CulturalGroup)relig).getClasses().size();
                break;
        }
        startAddress = relig.getFields().size();
        fillDataes();
    }

    private void fillDataes() {
        for (int position = 0; position < startRelig+startAddress+3; position++) {
            switch (relig.getType()){
                case Religious.MOSQUE:
                    fillMosque(position);
                    break;
                case Religious.HEIAT:
                    fillHeiat(position);
                    break;
                case Religious.CULTURAL:
                case Religious.QURAN:
                    fillCultural(position);
                    break;
            }
            fillRelig(position);
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.private_info,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv_key.setText(keys.get(position));
        holder.tv_value.setText(values.get(position));
        holder.btn_call.setVisibility(View.GONE);
        if (position == phoneIndex){
            holder.btn_call.setVisibility(View.VISIBLE);
            holder.btn_call.setTag(relig.getPhoneNum());
            holder.btn_call.setOnClickListener(this);
        }
    }

    private void fillRelig(int position) {
        int index = position - startRelig;
        if (index < 0)
            return;
        if (index < startAddress){
            if (!relig.getFields().get(index).getName().isEmpty()){
                keys.add(relig.getFields().get(index).getName());
                values.add(relig.getFields().get(index).getDes());
            }
        }else {
            switch (index - startAddress){
                case 0:
                    keys.add(context.getString(R.string.address));
                    values.add(relig.getAddress());
                    break;
                case 1:
                    keys.add(context.getString(R.string.phone));
                    values.add(relig.getPhoneNum());
                    phoneIndex = values.size() -1;
                    break;
                case 2:
                    keys.add(context.getString(R.string.connection_way));
                    values.add(relig.getConnectionWay());
                    break;
            }
        }
    }
    private void fillCultural(int position) {
        CulturalGroup cul = (CulturalGroup) relig;
        switch (position){
            case 0:
                keys.add(context.getString(R.string.boss_c));
                values.add(cul.getBossName());
                break;
            case 1:
                if (!cul.getHistory().isEmpty()){
                    keys.add(context.getString(R.string.history));
                    values.add(cul.getHistory());
                }
                break;
            case 2:
                if (!cul.getParent().isEmpty()){
                    keys.add(context.getString(R.string.depend));
                    values.add(cul.getParent());
                }
                break;
            case 3:
                if (!cul.getUserConditional().isEmpty()){
                    keys.add(context.getString(R.string.conditional));
                    values.add(cul.getUserConditional());
                }
                break;
            default:
                if (position >= startRelig)
                    return;
                int index = position - 4;
                if (!cul.getClasses().get(index).getName().isEmpty()){
                    String str = context.getString(R.string.class_c) + " " + cul.getClasses().get(index).getName();
                    keys.add(str);
                    String val = context.getString(R.string.manager) + " " + cul.getClasses().get(index).getManager()
                            +"\n"+context.getString(R.string.time) + " " + cul.getClasses().get(index).getTime();
                    values.add(val);
                }
                break;
        }
    }
    private void fillHeiat(int position) {
        Heiat hei = (Heiat) relig;
        switch (position){
            case 0:
                keys.add(context.getString(R.string.boss_h));
                values.add(hei.getBossName());
                break;
            case 1:
                if (!hei.getMadahsName().isEmpty()){
                    keys.add(context.getString(R.string.madah));
                    values.add(hei.getMadahsName());
                }
                break;
            case 2:
                if (!hei.getRoozehsName().isEmpty()){
                    keys.add(context.getString(R.string.roozeh));
                    values.add(hei.getRoozehsName());
                }
                break;
            case 3:
                if (!hei.getSpeakersName().isEmpty()){
                    keys.add(context.getString(R.string.speaker));
                    values.add(hei.getSpeakersName());
                }
                break;
            case 4:
                if (!hei.getChildren().isEmpty()){
                    keys.add(context.getString(R.string.children_h));
                    values.add(hei.getChildren());
                }
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    private void fillMosque(int position) {
        Mosque mos = (Mosque) relig;
        switch (position){
            case 0:
                keys.add(context.getString(R.string.emam_name));
                values.add(mos.getEmam_name());
                break;
            case 1:
                StringBuilder sb = new StringBuilder();
                if (mos.isPray_s())
                    sb.append(context.getString(R.string.prayer_s));
                if (mos.isPray_z())
                    sb.append(context.getString(R.string.prayer_z));
                if (mos.isPray_m())
                    sb.append(context.getString(R.string.prayer_m));
                if (!sb.toString().isEmpty()){
                    sb.append(context.getString(R.string.prayer_after));
                    keys.add(context.getString(R.string.prayer));
                    values.add(context.getString(R.string.prayer_before) + " "+sb.toString());
                }
                break;
            case 2:
                if (mos.isHasLibrary()){
                    keys.add(context.getString(R.string.library));
                    values.add(mos.getLibEmpty() +" "+context.getString(R.string.person));
                }
                break;
            case 3:
                if (!mos.getChildren().isEmpty()){
                    keys.add(context.getString(R.string.children_m));
                    values.add(mos.getChildren());
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    @Override
    public void onClick(View view) {
        String phone = (String) view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:"+phone));
        context.startActivity(intent);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tv_key,tv_value;
        MaterialButton btn_call;
        View view;
        public Holder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tv_key = itemView.findViewById(R.id.tv_key);
            tv_value = itemView.findViewById(R.id.tv_value);
            btn_call = itemView.findViewById(R.id.btn_call);
        }
    }
}
