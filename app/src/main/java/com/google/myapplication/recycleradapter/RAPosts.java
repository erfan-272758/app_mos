package com.google.myapplication.recycleradapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.activity.CardDialog;
import com.google.myapplication.activity.SharePostDialog;
import com.google.myapplication.ui.Design;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PAIV;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class RAPosts extends RecyclerView.Adapter<RAPosts.Holder> {
    int id;
    FragmentActivity context;
    public RAPosts(@LayoutRes int id, FragmentActivity context){
        this.id = id;
        this.context = context;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(id,parent,false);
        Holder holder = new Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        PAIV adapter = new PAIV(context);
        holder.vp.setAdapter(adapter);
        Design.createDots(context,holder.linear,4);
        holder.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Design.updateDots(context,holder.linear,position);
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewPager2 vp;
        LinearLayout linear;
        MaterialButton btn_share,btn_card;
        public Holder(@NonNull View itemView) {
            super(itemView);
            View v = itemView;
            if (id == R.layout.post_out_user)
                v = itemView.findViewById(R.id.post_out).findViewById(R.id.post);
            if (id == R.layout.post_out)
                v=itemView.findViewById(R.id.post);
            vp = v.findViewById(R.id.view_pager);
            linear = v.findViewById(R.id.linear);
            btn_share = v.findViewById(R.id.btn_share);
            btn_card = v.findViewById(R.id.btn_card);
            btn_share.setOnClickListener(this);
            btn_card.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_share){
                SharePostDialog dialog = new SharePostDialog(context);
                dialog.show();
            }
            if (view.getId() == R.id.btn_card){
                CardDialog dialog = new CardDialog(context);
                dialog.show();
            }
        }
    }
}
