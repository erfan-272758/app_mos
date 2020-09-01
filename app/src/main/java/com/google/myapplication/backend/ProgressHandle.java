package com.google.myapplication.backend;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.google.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ProgressHandle implements View.OnTouchListener {
    ChangeState cs;
    float oldY =0;
    boolean visible_up = false,visible_down = false;
    ProgressBar pb_down,pb_top;
    RecyclerView rv;
    Animation anim_in;
    public ProgressHandle(Context context,ChangeState cs, @NonNull ProgressBar pb_top, @Nullable ProgressBar pb_down,RecyclerView rv){
        this.cs = cs;
        this.pb_top = pb_top;
        this.pb_down = pb_down;
        this.rv = rv;
        anim_in = AnimationUtils.loadAnimation(context,R.anim.anim_in);
    }
    public static void animOut(ProgressBar pb,Animation anim){
        pb.setAnimation(anim);
        anim.start();
        pb.invalidate();
        pb.setVisibility(View.GONE);
    }
    public static void animOut(Context context, ProgressBar pb){
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.anim_out);
        animOut(pb,anim);
    }
    public void animIn(ProgressBar pb){
        pb.setVisibility(View.VISIBLE);
        pb.setAnimation(anim_in);
        anim_in.start();
        pb.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float minDis = (float) rv.getHeight() /6;
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                oldY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dis = motionEvent.getY() - oldY;
                if (pb_top.getVisibility() == View.GONE){
                    if (!rv.canScrollVertically(-1) && dis > minDis){
                        visible_up = true;
                    }
                }
                if (pb_down != null){
                    if (pb_down.getVisibility() == View.GONE){
                        if (!rv.canScrollVertically(1) && -dis > minDis){
                            visible_down = true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (pb_down != null){
                    if (visible_down){
                        animIn(pb_down);
                        cs.extra();
                        visible_down = false;
                    }
                }
                if (visible_up){
                    animIn(pb_top);
                    cs.update();
                    visible_up = false;
                }
        }
        return false;
    }

    public interface ChangeState{
        void update();
        void extra();
    }
}
