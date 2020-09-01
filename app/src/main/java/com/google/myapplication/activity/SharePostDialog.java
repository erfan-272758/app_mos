package com.google.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.myapplication.R;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.backend.ProgressHandle;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.recycleradapter.RASharePost;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SharePostDialog extends AppCompatDialog implements View.OnClickListener, RecyclerView.OnItemTouchListener, ProgressHandle.ChangeState, SearchView.OnQueryTextListener {
    private MainData.Post post;
    private Holder holder;
    boolean click = false;
    ArrayList<MainData.Person> people;
    public SharePostDialog(Context context,ArrayList<MainData.Person> people,MainData.Post post) {
        super(context,R.style.DialogStyle);
        setContentView(R.layout.dialog_share_post);
        this.people = people;
        this.post = post;
        init();
        setSetting();
    }

    private void setSetting() {
        //Size
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void init() {
        holder = new Holder();
        //Recycler View
        holder.rv.setAdapter(holder.adapter);
        holder.rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        holder.rv.addOnItemTouchListener(this);
        holder.rv.setOnTouchListener(new ProgressHandle(getContext(),this,holder.pb,null,holder.rv));
        //Button
        holder.btn_plus.setOnClickListener(this);
        //Search View
        holder.sv.setOnQueryTextListener(this);
    }

    @Override
    public void onClick(View view) {
        PrepareData.sendSuggestForAll(getContext(),post.getId_act(), holder.edit_des.getText().toString().trim(),
                post.getDes_act(),post.getAct_rate());
        dismiss();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        switch (e.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                click = true;
                break;
            case MotionEvent.ACTION_MOVE:
                click = false;
                break;
            case MotionEvent.ACTION_UP:
                if (click){
                    click = false;
                    View view = rv.findChildViewUnder(e.getX(),e.getY());
                    PrepareData.sendSuggest(getContext(),(String) view.getTag(),post.getId_act(),
                            holder.edit_des.getText().toString().trim(),post.getDes_act(),post.getAct_rate());
                    dismiss();
                }
                break;
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void update() {
        holder.adapter.update(PrepareData.getNewSuggestWO(holder.pb));
    }

    @Override
    public void extra() {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        ArrayList<MainData.Person> people = PrepareData.getSearchPerson(s);
        holder.adapter.update(people);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ArrayList<MainData.Person> people = PrepareData.getSearchPerson(s);
        holder.adapter.update(people);
        return false;
    }

    class Holder{
        RecyclerView rv;
        SearchView sv;
        MaterialButton btn_plus;
        TextInputEditText edit_des;
        RASharePost adapter;
        ProgressBar pb;
        Holder (){
            rv = findViewById(R.id.re_view);
            sv = findViewById(R.id.search);
            btn_plus = findViewById(R.id.btn_plus);
            edit_des = findViewById(R.id.edit_des);
            adapter = new RASharePost(getContext(),people);
            pb = findViewById(R.id.pb_top);
        }
    }
}
