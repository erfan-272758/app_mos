package com.google.myapplication.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.google.myapplication.ui.Design;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class FragmentSuggestList extends Fragment {
    String describe_p,name_o,uri_im_o,describe_o;
    boolean seen;
    int act_id=-1;
    float act_rate;
    Holder holder;
    public static FragmentSuggestList newInstance(String describe_p,String name_o,String uri_im_o,String describe_o,
                                                  boolean seen,int act_id,float act_rate){
        FragmentSuggestList fragment = new FragmentSuggestList();
        Bundle b = fragment.makeBundle(describe_p,name_o,uri_im_o,describe_o,seen,act_id,act_rate);
        fragment.setArguments(b);
        return fragment;
    }

    private Bundle makeBundle(String describe_p,String name_o,String uri_im_o,String describe_o,boolean seen,
                                                  int act_id,float act_rate){
        Bundle b = new Bundle();
        b.putInt("act_id",act_id);
        b.putString("describe_p",describe_p);
        b.putString("describe_o",describe_o);
        b.putString("name_o",name_o);
        b.putString("uri_im_o",uri_im_o);
        b.putBoolean("seen",seen);
        b.putFloat("act_rate",act_rate);
        return b;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        init(getArguments());
        View view = inflater.inflate(R.layout.suggest_list,container,false);
        holder = new Holder(view);
        initHolder();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowPostsActivity.class);
                PostParcel pp = new PostParcel();
                pp.addPosts(((MainData.Data)getActivity()).getMainData());
                intent.putExtra("act_id",act_id);
                intent.putExtra("main_data",pp);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    private void initHolder() {
        holder.tv_des_p.setText(describe_p);
        holder.tv_des_o.setText(describe_o);
        holder.tv_name_o.setText(name_o);
        holder.rb.setRating(act_rate);
        if ("".equals(uri_im_o)){
            holder.iv_o.setImageDrawable(getContext().getDrawable(R.drawable.person));
        }else{
            Design.showImage(getContext(),holder.cv,uri_im_o,holder.iv_o,R.drawable.person,null);
        }
        changeColor();
    }

    private void init(@NonNull Bundle b) {
        describe_p = b.getString("describe_p","");
        describe_o = b.getString("describe_o","");
        name_o = b.getString("name_o","");
        uri_im_o = b.getString("uri_im_o","");
        seen = b.getBoolean("seen",false);
        act_id = b.getInt("act_id",-1);
    }

    public void changeColor(){
        if (seen)
            holder.cv.setCardBackgroundColor(Color.WHITE);
        else
            holder.cv.setCardBackgroundColor(ContextCompat.getColor(getContext(),R.color.cardColor));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (act_id!=-1)
            outState = makeBundle(describe_p,name_o,uri_im_o,describe_o,seen,act_id,act_rate);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null && savedInstanceState.getInt("act_id",-1) != -1){
            init(savedInstanceState);
        }
    }

    class  Holder {
        TextView tv_des_p , tv_des_o , tv_name_o;
        ImageView iv_o;
        RatingBar rb;
        CardView cv;
        Holder(View view){
            tv_des_o = view.findViewById(R.id.text_des_o);
            tv_des_p = view.findViewById(R.id.text_des_p);
            tv_name_o = view.findViewById(R.id.text_name_o);
            iv_o = view.findViewById(R.id.image_o);
            rb = view.findViewById(R.id.rating);
            cv = view.findViewById(R.id.card_view);
        }
    }
}