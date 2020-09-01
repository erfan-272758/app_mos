package com.google.myapplication.recycleradapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.R;
import com.google.myapplication.activity.ReligiousReadActivity;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RAFollow extends RecyclerView.Adapter<RAFollow.Holder> implements View.OnClickListener {
    Context context;
    ArrayList<MainData.Organ> organs;
    public RAFollow(Context context, ArrayList<MainData.Organ> organs) {
        this.context = context;
        this.organs= organs;
    }
    public void update(ArrayList<MainData.Organ> newOrgans){
        organs.clear();
        organs.addAll(newOrgans);
        notifyDataSetChanged();
    }
    public void extra(ArrayList<MainData.Organ> newOrgans){
        organs.addAll(newOrgans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_organ_follow,parent,false);
        return new RAFollow.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv_name.setText(organs.get(position).getName_o());
        holder.tv_bio.setText(organs.get(position).getBio());
        Design.showImage(context,holder.iv,organs.get(position).getUri_o(),holder.iv,R.drawable.person,null);
        holder.ll.setTag(organs.get(position));
        holder.iv.setTag(organs.get(position));
        holder.btn_plus.setTag(organs.get(position));
        holder.ll.setOnClickListener(this);
        holder.iv.setOnClickListener(this);
        setFollowUi(holder.btn_plus,organs.get(position));
        holder.btn_plus.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return organs.size();
    }

    @Override
    public void onClick(View view) {
        MainData.Organ organ = (MainData.Organ) view.getTag();
        if (view.getId() == (R.id.linear | R.id.image)){
            //Religious Read
            Intent intent = new Intent(context, ReligiousReadActivity.class);
            Bundle b = new Bundle();
            b.putString("id_o",organ.getId_o());
            b.putString("name_o",organ.getName_o());
            b.putString("uri_o",organ.getUri_o());
            b.putString("bio_o",organ.getBio());
            b.putBoolean("follow_o",organ.isFollow());
            b.putInt("act_num",organ.getAct_num());
            b.putInt("follow_num",organ.getFollow_num());
            intent.putExtra("bundle",b);
            context.startActivity(intent);
        }else if (view.getId() == R.id.btn_plus){
            //Follow
            organ.setFollow(!organ.isFollow());
            setFollowUi((MaterialButton) view,organ);
        }
    }
    private void setFollowUi(MaterialButton btn, MainData.Organ organ){
        if (organ.isFollow()){
            btn.setIcon(context.getDrawable(R.drawable.tick));
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorWhiteGreen));
            btn.invalidate();
            organ.setFollow_num(organ.getFollow_num()+1);
            PrepareData.changeFollowOrgan(organ.getId_o(),true);

        }else {
            btn.setIcon(context.getDrawable(R.drawable.plus_image));
            btn.setBackgroundColor(ContextCompat.getColor(context,R.color.colorBlue));
            btn.invalidate();
            organ.setFollow_num(organ.getFollow_num()-1);
            PrepareData.changeFollowOrgan(organ.getId_o(),false);
        }
    }
    public class Holder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_name,tv_bio;
        MaterialButton btn_plus;
        LinearLayout ll;
        public Holder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image);
            tv_bio = itemView.findViewById(R.id.text_bio);
            tv_name = itemView.findViewById(R.id.text_name);
            btn_plus = itemView.findViewById(R.id.btn_plus);
            ll = itemView.findViewById(R.id.linear);
        }
    }
}
