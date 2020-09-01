package com.google.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.dataclass.PersonHistory;
import com.google.myapplication.pageradapter.PAPerson;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

public class PersonReadActivity extends AppCompatActivity {
    MainData.Person person;
    Holder holder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_read);
        initIntent();
        initHolder();

    }

    private void initHolder() {
        holder = new Holder();
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.getBundleExtra("bundle") != null )
            readFromBundle(intent.getBundleExtra("bundle"));
    }

    class Holder implements View.OnClickListener {
        Toolbar toolbar;
        TextView tv_id,tv_name,tv_bio;
        ImageView iv;
        MaterialButton btn_follow;
        TabLayout tl;
        ViewPager vp;
        PAPerson adapter;
        Holder(){
            toolbar = findViewById(R.id.toolbar);
            tv_id = findViewById(R.id.text_id);
            tv_name = findViewById(R.id.text_name);
            tv_bio = findViewById(R.id.text_bio);
            iv = findViewById(R.id.image);
            btn_follow = findViewById(R.id.btn_follow);
            tl = findViewById(R.id.tabl);
            vp = findViewById(R.id.view_pager_1);
            adapter = new PAPerson(getSupportFragmentManager(),person.getId_p());
            initHolder();
        }
        private void initHolder(){
            //Tool Bar
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            tv_id.setText(person.getId_p());
            //body
            Design.showImage(PersonReadActivity.this,iv,person.getUri_p(),iv,R.drawable.person,null);
            tv_name.setText(person.getName_p());
            tv_bio.setText(person.getBio());
            changeBtnFollow();
            btn_follow.setOnClickListener(this);
            //View Pager
            Design.setViewPager(vp,adapter,tl,new int[]{R.drawable.history,R.drawable.follow});
        }
        private void changeBtnFollow(){
            if (person.isFollow()){
                btn_follow.setText(R.string.follow_per_ok);
                btn_follow.setBackgroundColor(ContextCompat.getColor(PersonReadActivity.this,R.color.colorWhite));
                btn_follow.setTextColor(ContextCompat.getColor(PersonReadActivity.this,R.color.colorBlack));
            }else {
                btn_follow.setText(R.string.follow_per_none);
                btn_follow.setBackgroundColor(ContextCompat.getColor(PersonReadActivity.this,R.color.colorDarkBlue));
                btn_follow.setTextColor(ContextCompat.getColor(PersonReadActivity.this,R.color.colorWhite));
            }
        }

        @Override
        public void onClick(View view) {
            person.setFollow(!person.isFollow());
            changeBtnFollow();
            PrepareData.changeFollowPerson(person.getId_p(),person.isFollow());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void readFromBundle(Bundle bundle){
        String name = bundle.getString("per_name","");
        String uri = bundle.getString("uri_im","");
        String id = bundle.getString("per_id","");
        boolean follow = bundle.getBoolean("follow",false);
        String bio = bundle.getString("bio","");
        person = new MainData.Person(name,bio,uri,id,follow);
    }
}
