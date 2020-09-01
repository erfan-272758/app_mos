package com.google.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.myapplication.R;
import com.google.myapplication.backend.PostParcel;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.fragments.home.FragmentHistory;
import com.google.myapplication.pageradapter.PAShowPosts;
import com.google.myapplication.ui.Design;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class ShowPostsActivity extends AppCompatActivity implements MainData.Data {
    ArrayList<MainData> mds;
    int act_id;
    int[] pos;
    int[] onReadBack;
    int[] onReadFront;
    boolean turn_up = true;
    int count = 0;
    //    int lastFront ,lastBack,lastFront0 ,lastBack0;
//    boolean read_back = true,read_front = true,read_back0 = true,read_front0 = true;
//    boolean turnFront=false,turnFront0=false;
    PAShowPosts adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sugest);
        onReadBack = new int[]{0,0};
        onReadFront = new int[]{0,0};
        init();
    }

    private void init() {
        //Intent
        Intent intent = getIntent();
        PostParcel pp = intent.getParcelableExtra("main_data");
        mds = pp.getPosts();
        act_id = intent.getIntExtra("act_id",-1);
        if (act_id == -1)
            finish();
        int type = mds.get(0).getPerson() == null ? FragmentHistory.NO_PERSON : FragmentHistory.WITH_PERSON;
        adapter = new PAShowPosts(this, type);
        setPos();
        addMainDataFirst(pos[0]);
        addPostFirst(pos[0],pos[1]);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new LoadData(LoadData.BACK));
        es.execute(new LoadData(LoadData.FRONT));
        //Fill View
        ViewPager2 vp2 = findViewById(R.id.view_pager);
        vp2.setAdapter(adapter);
    }

    private void setPos() {
        pos = new int[]{0,0};
        for (MainData md:mds){
            for (MainData.Post post:md.getPosts()){
                if (act_id == post.getId_act())
                    return;
                else
                    pos[1]++;
            }
            pos[1]=0;
            pos[0]++;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Design.noStatusColor(this);
    }

    @Override
    public ArrayList<MainData> getMainData() {
        return null;
    }

    @Override
    public ArrayList<MainData.Person> getListPerson() {
        ArrayList<MainData.Person> people = new ArrayList<>();
        for (MainData md:mds) {
            people.add(md.getPerson());
        }
        return people;
    }
    class LoadData extends Thread{
        public static final int BACK = -2;
        public static final int FRONT = -3;

        int type;
        LoadData(int type){
            this.type = type;
        }

        @Override
        public void run() {
            if (type == BACK){
                for (int i = pos [0];i >= 0;i--){
                    onReadBack[0] = i;
                    addMainDataFirst(i);
                    for (int j = pos[1]-1;j>=0;j--){
                        if (turn_up) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        onReadBack[1] = j;
                        addPostFirst(i,j);
                        turn_up = true;
                        ShowPostsActivity.this.notify();
                    }
                }
            }
            if (type == FRONT){
                for (int i = pos [0];i < mds.size();i++){
                    onReadFront[0] = i;
                    addMainDataLast(i);
                    for (int j = pos[1]+1;j<mds.get(i).getPosts().size();j++){
                        if (!turn_up){
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        onReadFront[1] = j;
                        addPostLast(i,j);
                        count++;
                        if (count == 3){
                            count = 0;
                            turn_up = false;
                            ShowPostsActivity.this.notify();
                        }
                    }
                }
            }
        }
    }

    synchronized private void addMainDataFirst(int pos0){
        adapter.addMainDataFirst(mds.get(pos0));
    }
    synchronized private void addMainDataLast(int pos0){
        adapter.addMainDataLast(mds.get(pos0));
    }
    synchronized private void addPostFirst(int pos0,int pos1){
        PrepareData.fillPost(mds.get(pos0).getPosts().get(pos1));
        adapter.addPostFirst(mds.get(pos0).getPosts().get(pos1),mds.get(pos0));
    }
    synchronized private void addPostLast(int pos0,int pos1){
        PrepareData.fillPost(mds.get(pos0).getPosts().get(pos1));
        adapter.addPostLast(mds.get(pos0).getPosts().get(pos1),mds.get(pos0));
    }
}
