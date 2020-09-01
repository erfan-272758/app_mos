package com.google.myapplication.recycleradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RASharePost extends RecyclerView.Adapter<RASharePost.Holder> {
    Context context;
    ArrayList<MainData.Person> people;
    public RASharePost(Context context, ArrayList<MainData.Person> people) {
        this.context = context;
        this.people = people;
    }
    public void update(ArrayList<MainData.Person> newPeople){
        people.clear();
        people.addAll(newPeople);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.follow_re_view,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemView.setTag(people.get(position).getId_p());
        holder.tv_name.setText(people.get(position).getName_p());
        Design.showImage(context,holder.itemView,people.get(position).getUri_p(),holder.iv,R.drawable.person,null);
    }

    @Override
    public int getItemCount() {
        return Math.min(people.size(),10);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_name;
        public Holder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image_p);
            tv_name = itemView.findViewById(R.id.text_name_p);
        }
    }
}
