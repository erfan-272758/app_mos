package com.google.myapplication.recycleradapter;

import android.content.Context;
import android.os.Build;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RAConversation extends RecyclerView.Adapter<RAConversation.Holder> {
    Context context;
    int[] size;
    public RAConversation(Context context) {
        this.context = context;
        size = new int[30];
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversation_re_view,parent,false);
        return new RAConversation.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if ((position % 2) == 1){
            holder.cv.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorBlackWhiteGray));
            holder.tv.setText("سلام خوبم تو چطوری\n\nسلام خوبم تو چطوری\nسلام خوبم تو چطوری سلام خوبم تو چطوری؟");
            holder.lr.setGravity(Gravity.RIGHT);
            holder.lr.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            holder.iv.setImageDrawable(context.getDrawable(R.drawable.person));
        }else {
            holder.cv.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorWhite));
            holder.tv.setText("سلام خوبی چه خبرا؟؟؟\nسلام خوبی چه خبرا؟؟؟سلام خوبی چه خبرا؟؟؟");
            holder.lr.setGravity(Gravity.LEFT);
            holder.lr.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            holder.iv.setImageDrawable(context.getDrawable(R.drawable.person1));
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class Holder extends RecyclerView.ViewHolder {
        LinearLayout lr;
        CardView cv;
        TextView tv;
        ImageView iv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.card_view);
            tv = itemView.findViewById(R.id.text_view);
            lr = itemView.findViewById(R.id.linear);
            iv = itemView.findViewById(R.id.image);
        }
    }
}
