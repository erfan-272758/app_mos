package com.google.myapplication.recycleradapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.myapplication.R;
import com.google.myapplication.activity.ShowPostsActivity;
import com.google.myapplication.backend.PostParcel;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.dataclass.PersonHistory;
import com.google.myapplication.fragments.home.FragmentHistory;
import com.google.myapplication.pageradapter.PAPerson;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RAHistory extends RecyclerView.Adapter<RAHistory.Holder> {
    Context context;
    ArrayList<PersonHistory> history;
    PostParcel pp;
    public RAHistory(Context context, ArrayList<PersonHistory> history) {
        this.context = context;
        this.history = history;
        fillPostParcel();
    }
    public void update(ArrayList<PersonHistory> newHistory){
        history.clear();
        history.addAll(newHistory);
        updatePostParcel(newHistory);
        notifyDataSetChanged();
    }
    public void extra(ArrayList<PersonHistory> newHistory){
        history.addAll(newHistory);
        extraPostParcel(newHistory);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.suggest_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final PersonHistory history = this.history.get(position);
        holder.tv_des_p.setText(history.getPer_des());
        holder.tv_des_o.setText(history.getAct_des());
        holder.tv_name_o.setText(history.getOrgan().getName_o());
        holder.rb.setRating(history.getRate_act());
        if ("".equals(history.getOrgan().getUri_o())){
            holder.iv_o.setImageDrawable(context.getDrawable(R.drawable.person));
        }else{
            Design.showImage(context,holder.cv,history.getOrgan().getUri_o(),holder.iv_o,R.drawable.person,null);
        }
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowPostsActivity.class);
                intent.putExtra("act_id",history.getAct_id());
                intent.putExtra("main_data",pp);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_des_p , tv_des_o , tv_name_o;
        ImageView iv_o;
        RatingBar rb;
        CardView cv;
        Holder(@NonNull View view){
            super(view);
            tv_des_o = view.findViewById(R.id.text_des_o);
            tv_des_p = view.findViewById(R.id.text_des_p);
            tv_name_o = view.findViewById(R.id.text_name_o);
            iv_o = view.findViewById(R.id.image_o);
            rb = view.findViewById(R.id.rating);
            cv = view.findViewById(R.id.card_view);
        }
    }
    private void fillPostParcel(){
        ArrayList<MainData>mds = new ArrayList<>();
        for (PersonHistory his:RAHistory.this.history) {
            MainData md = new MainData();
            ArrayList<MainData.Organ> organs = new ArrayList<>();
            organs.add(his.getOrgan());
            md.setOrgans(organs);
            mds.add(md);
        }
        pp = new PostParcel();
        pp.addPosts(mds);
    }
    private void extraPostParcel(ArrayList<PersonHistory> histories){
        ArrayList<MainData>mds = new ArrayList<>();
        for (PersonHistory his:histories) {
            MainData md = new MainData();
            ArrayList<MainData.Organ> organs = new ArrayList<>();
            organs.add(his.getOrgan());
            md.setOrgans(organs);
            mds.add(md);
        }
        if (pp == null)
            pp = new PostParcel();
        pp.addPosts(mds);

    }
    private void updatePostParcel(ArrayList<PersonHistory> histories){
        ArrayList<MainData>mds = new ArrayList<>();
        for (PersonHistory his:histories) {
            MainData md = new MainData();
            ArrayList<MainData.Organ> organs = new ArrayList<>();
            organs.add(his.getOrgan());
            md.setOrgans(organs);
            mds.add(md);
        }
        if (pp == null)
            pp = new PostParcel();
        pp.updatePosts(mds);

    }
}
