package com.google.myapplication.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.pageradapter.PAReligiousRead;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class ReligiousReadActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{
    boolean showStart = false;
    float oldY = 0f;
    MainData.Organ organ;
    Holder holder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religious_read);
        initIntent();
        init();
    }


    private void initIntent() {
        if (getIntent() != null){
            Bundle b = getIntent().getBundleExtra("bundle");
            if (b != null)
                readFromBundle(b);
        }
    }

    private void readFromBundle(Bundle bundle) {
        String name = bundle.getString("name_o","");
        String id = bundle.getString("id_o","");
        String uri = bundle.getString("uri_o","");
        String bio = bundle.getString("bio_o","");
        boolean follow = bundle.getBoolean("follow_o",false);
        int follow_num = bundle.getInt("follow_num",0);
        int act_num = bundle.getInt("act_num",0);
        organ = new MainData.Organ(name,uri,id,follow,bio,act_num,follow_num);
    }

    private void init() {
        holder = new Holder();
        setSupportActionBar(holder.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //topic
        holder.tv_id.setText(organ.getId_o());
        holder.tv_bio.setText(organ.getBio());
        holder.tv_name.setText(organ.getName_o());
        holder.tv_act_num.setText(organ.getAct_num());
        holder.tv_follow_num.setText(organ.getFollow_num());
        Design.showImage(this,holder.rl,organ.getUri_o(),holder.iv,R.drawable.person,null);
        //body
        Design.setViewPager(holder.vp,holder.adapter,holder.tl,new int[]{R.drawable.outline,R.drawable.activityinner});
        //Button
        holder.btn_more.setOnClickListener(this);
        holder.btn_more.setOnTouchListener(this);
        holder.btn_conversation.setOnClickListener(this);
    }
    public void showPager(){

        ValueAnimator value = ValueAnimator.ofInt(holder.rl.getHeight());
        value.setDuration(900L);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                holder.rl1.setTranslationY(-(Integer) valueAnimator.getAnimatedValue());
            }
        });
        value.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                holder.rl1.setTranslationY(0);
                holder.rl.setVisibility(View.GONE);
                holder.btn_more.setIcon(getDrawable(R.drawable.more_down));
                holder.rl1.invalidate();
                holder.rl.invalidate();
//                Log.i("test",""+vp2.getCurrentItem());
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        value.start();

    }
    public void hiddenPager(){
        final float oldY = holder.rl1.getTranslationY();
        ValueAnimator value = ValueAnimator.ofInt(holder.rl.getHeight());
        value.setDuration(900L);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                holder.rl1.setTranslationY((Integer) valueAnimator.getAnimatedValue());
            }
        });
        value.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                holder.rl1.setTranslationY(oldY);
                holder.rl.setVisibility(View.VISIBLE);
                holder.btn_more.setIcon(getDrawable(R.drawable.more_up));
                holder.rl1.invalidate();
                holder.rl.invalidate();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        value.start();

    }
    @Override
    public void onClick(View view) {
        Log.i("check1", "onT");
        switch (view.getId()){
            case R.id.btn_more://btn_more
                if (holder.rl.getVisibility() == View.VISIBLE)
                    showPager();
                else
                    hiddenPager();
                break;
            case R.id.btn_conversation:
                Intent intent = new Intent(this, ConversationActivity.class);
                Bundle b = new Bundle();
                b.putString("id_o",organ.getId_o());
                b.putString("name_o",organ.getName_o());
                b.putString("uri_o",organ.getUri_o());
                b.putString("bio_o",organ.getBio());
                b.putBoolean("follow_o",organ.isFollow());
                b.putInt("act_num",organ.getAct_num());
                b.putInt("follow_num",organ.getFollow_num());
                intent.putExtra("bundle",b);
                startActivity(intent);
                break;
        }
//        main.invalidate();
    }
    @Override
    public void onBackPressed() {
        if (holder.rl.getVisibility() == View.GONE)
            hiddenPager();
        else
            super.onBackPressed();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                oldY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (view.getId() == holder.btn_more.getId()){
                    if (oldY != 0){
                        int i = holder.rl.getVisibility() == View.VISIBLE ? -1 : 1;
                        float dis = i * (motionEvent.getY() - oldY);
                        if (dis > 10f ){
                            showStart = true;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (showStart){
                    if (holder.rl.getVisibility() == View.VISIBLE) {
                        showPager();
                    } else {
                        hiddenPager();
                    }
//                    main.invalidate();
                }
                showStart = false;
                oldY = 0;
                break;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    class Holder implements View.OnClickListener {
        RelativeLayout rl,rl1;
        MaterialButton btn_more,btn_conversation,btn_follow;
        ViewPager vp;
        Toolbar toolbar;
        PAReligiousRead adapter;
        TabLayout tl;
        TextView tv_name,tv_bio,tv_follow_num,tv_act_num,tv_id;
        ImageView iv;
        Holder(){
            rl = findViewById(R.id.relative);
            rl1 = findViewById(R.id.relative1);
            toolbar = findViewById(R.id.toolbar);
            tl = findViewById(R.id.tabl);
            vp = findViewById(R.id.view_pager_1);
            adapter = new PAReligiousRead(getSupportFragmentManager(),organ.getId_o());
            btn_more = findViewById(R.id.btn_more);
            btn_conversation = findViewById(R.id.btn_conversation);
            btn_follow = findViewById(R.id.btn_follow);
            btn_follow.setOnClickListener(this);
            tv_name = findViewById(R.id.text_name);
            tv_act_num = findViewById(R.id.text_act_num);
            tv_follow_num= findViewById(R.id.text_follow_num);
            tv_bio = findViewById(R.id.text_bio);
            tv_id = findViewById(R.id.text_id);
            iv = findViewById(R.id.image);
        }
        private void changeBtnFollow(){
            if (organ.isFollow()){
                btn_follow.setText(R.string.follow_org_ok);
                btn_follow.setBackgroundColor(ContextCompat.getColor(ReligiousReadActivity.this,R.color.colorWhite));
                btn_follow.setTextColor(ContextCompat.getColor(ReligiousReadActivity.this,R.color.colorBlack));
            }else {
                btn_follow.setText(R.string.follow_org_none);
                btn_follow.setBackgroundColor(ContextCompat.getColor(ReligiousReadActivity.this,R.color.colorDarkBlue));
                btn_follow.setTextColor(ContextCompat.getColor(ReligiousReadActivity.this,R.color.colorWhite));
            }
        }
        @Override
        public void onClick(View view) {
            organ.setFollow(!organ.isFollow());
            changeBtnFollow();
            PrepareData.changeFollowOrgan(organ.getId_o(),organ.isFollow());
        }
    }
}
