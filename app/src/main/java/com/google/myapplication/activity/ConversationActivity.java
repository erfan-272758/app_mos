package com.google.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.recycleradapter.RAConversation;

public class ConversationActivity extends AppCompatActivity {
    MainData.Organ organ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
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
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Recycler View
        RecyclerView rv = findViewById(R.id.re_view);
        RAConversation adapter = new RAConversation(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}