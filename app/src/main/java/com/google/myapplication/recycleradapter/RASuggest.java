package com.google.myapplication.recycleradapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.myapplication.R;
import com.google.myapplication.activity.PersonReadActivity;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.pageradapter.PASuggestList;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class RASuggest extends RecyclerView.Adapter<RASuggest.Holder> implements View.OnClickListener {
    FragmentActivity fa;
    ArrayList<MainData> suggests;

    public RASuggest(FragmentActivity fa) {
        this.fa = fa;
        suggests = new ArrayList<>();
    }

    public void addSuggest(MainData ps){
        suggests.add(ps);
        notifyDataSetChanged();
    }
    public void addSuggests(ArrayList<MainData> newS){
        suggests.addAll(newS);
        notifyDataSetChanged();
    }

    public ArrayList<MainData> getSuggests() {
        return suggests;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(fa).inflate(R.layout.sugest,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        MainData md = suggests.get(position);
        holder.tv_name.setText(md.getPerson().getName_p());
        //Load Image
        if ("".equals(md.getPerson().getUri_p()))
            holder.iv_p.setImageDrawable(fa.getDrawable(R.drawable.person));
        else
            Design.showImage(fa,holder.li_p,md.getPerson().getUri_p(),holder.iv_p,R.drawable.person,null);
        //View pager
        PASuggestList adapter = new PASuggestList(fa,md);
        holder.vp2.setAdapter(adapter);
        Design.createDots(fa,holder.li_dots,holder.vp2.getAdapter().getItemCount());
        holder.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Design.updateDots(fa,holder.li_dots,position);
            }
        });
        holder.vp2.setCurrentItem(findCurrentItem(md.getPosts()));
        //Linear person
        holder.li_p.setTag(md.getPerson());
        holder.li_p.setOnClickListener(this);
    }

    private int findCurrentItem(ArrayList<MainData.Post> posts) {
        for (int i = 0; i < posts.size(); i++) {
            if (!posts.get(i).isSeen())
                return i;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return suggests.size();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.linear_a){
            Intent intent = new Intent(fa, PersonReadActivity.class);
            MainData.Person person = (MainData.Person) view.getTag();
            Bundle bundle = new Bundle();
            bundle.putString("per_name",person.getName_p());
            bundle.putString("per_id",person.getId_p());
            bundle.putString("uri_im",person.getUri_p());
            bundle.putBoolean("follow",person.isFollow());
            bundle.putString("bio",person.getBio());
            intent.putExtra("bundle",bundle);
            fa.startActivity(intent);
        }
    }

    public void update(ArrayList<MainData> suggests){
        suggests.clear();
        suggests.addAll(suggests);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        LinearLayout li_p,li_dots;
        ViewPager2 vp2;
        ImageView iv_p;
        TextView tv_name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            //Linear
            li_p = itemView.findViewById(R.id.linear_a);
            iv_p = itemView.findViewById(R.id.image_p);
            tv_name = itemView.findViewById(R.id.text_name);
            //View Pager
            vp2 = itemView.findViewById(R.id.view_pager);
            li_dots = itemView.findViewById(R.id.linear);
        }
    }
}