package com.google.myapplication.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.activity.CardDialog;
import com.google.myapplication.activity.MapDialog;
import com.google.myapplication.activity.PersonReadActivity;
import com.google.myapplication.activity.ReligiousReadActivity;
import com.google.myapplication.activity.SharePostDialog;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.fragments.home.FragmentHistory;
import com.google.myapplication.ui.Design;
import com.google.myapplication.R;
import com.google.myapplication.pageradapter.PAIV;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentPost extends Fragment implements View.OnClickListener {
    MainData md = null;
    ArrayList<MainData.Person> people;
    int type;
    public static FragmentPost newInstance (MainData md,int pos,int type){
        FragmentPost f = new FragmentPost();
        f.setArguments(makeBundle(md, pos,type));
        return f;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle b = getArguments();
        if (b != null && b.getInt("id_act",-1) != -1){
            initArgs(b);
        }
        View itemView = inflater.inflate(R.layout.post_out_user,container,false);
        people = ((MainData.Data)(getActivity())).getListPerson();
        //Holder
        final Holder holder = new Holder(itemView);
        holder.initHolder();
        //View Pager
        PAIV adapter = new PAIV(getActivity(),md.getPosts().get(0).getUri_im_video(),md.getPosts().get(0).getFlags_im_video());
        holder.vp2.setAdapter(adapter);
        Design.createDots(getActivity(),holder.ll_dot,holder.vp2.getAdapter().getItemCount());
        holder.vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Design.updateDots(getActivity(),holder.ll_dot,position);
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        //Buttons
        holder.btn_share.setOnClickListener(this);
        holder.btn_card.setOnClickListener(this);
        holder.btn_map.setOnClickListener(this);
        //Rating
        holder.rb_per.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                if (b){
                    md.getPosts().get(0).setPer_rate(v);
                    float newRate = PrepareData.changePersonRate(v,md.getPosts().get(0).getId_act());
                    holder.rb_act.setRating(newRate);
                }
            }
        });
        return itemView;
    }
    private void initArgs(Bundle b) {
        md = new MainData();
        type = b.getInt("type", FragmentHistory.WITH_PERSON);
        if (type == FragmentHistory.WITH_PERSON){
            String name_p = b.getString("name_p","");
            String id_p = b.getString("id_p","");
            String uri_p = b.getString("uri_p","");
            boolean follow = b.getBoolean("follow",false);
            String bio = b.getString("bio",md.getPerson().getBio());
            MainData.Person person = new MainData.Person(name_p,bio,uri_p,id_p,follow);
            md.setPerson(person);
        }
        String name_o = b.getString("name_o","");
        String id_o = b.getString("id_o","");
        String uri_o = b.getString("uri_o","");
        String bio_o = b.getString("bio_o","");
        boolean follow = b.getBoolean("follow_o",false);
        int follow_num = b.getInt("follow_num",0);
        int act_num = b.getInt("act_num",0);
        MainData.Organ organ = new MainData.Organ(name_o,uri_o,id_o,follow,bio_o,act_num,follow_num);
        ArrayList<MainData.Organ> organs = new ArrayList<>();
        organs.add(organ);
        md.setOrgans(organs);
        String des_act = b.getString("des_act","");
        String label_rep = b.getString("label_rep","");
        String label_time = b.getString("label_time","");
        float per_rate = b.getFloat("per_rate",0f);
        float act_rate = b.getFloat("act_rate",0f);
        double lat = b.getDouble("lat",0d);
        double lon = b.getDouble("lon",0d);
        int id_act = b.getInt("id_act",-1);
        boolean help_card = b.getBoolean("help_card",false);
        ArrayList<Integer> arrV = b.getIntegerArrayList("flags_im_video");
        ArrayList<String> arrK = b.getStringArrayList("uri_im_video");
        MainData.Post post = new MainData.Post(id_act,arrK,arrV,des_act,act_rate,per_rate,label_rep,label_time);
        post.setMap(new MainData.Post.Map(lat,lon));
        if (help_card){
            post.setCard(new MainData.Post.Card(b.getString("name",""),
                    b.getString("card_num","")));
        }
        ArrayList<MainData.Post> posts = new ArrayList<>();
        posts.add(post);
        md.setPosts(posts);
    }
    private static Bundle makeBundle(MainData md,int pos,int type){
        Bundle b = new Bundle();
        b.putInt("type",type);
        if (type == FragmentHistory.WITH_PERSON){
            b.putString("id_p",md.getPerson().getId_p());
            b.putString("name_p",md.getPerson().getName_p());
            b.putString("uri_p", md.getPerson().getUri_p());
            b.putBoolean("follow",md.getPerson().isFollow());
            b.putString("bio",md.getPerson().getBio());
        }
        MainData.Organ organ = md.getOrgans().get(pos);
        b.putString("name_o",organ.getName_o());
        b.putString("uri_o",organ.getUri_o());
        b.putString("id_o",organ.getId_o());
        b.putString("bio_o",organ.getBio());
        b.putBoolean("follow_o",organ.isFollow());
        b.putInt("act_num",organ.getAct_num());
        b.putInt("follow_num",organ.getFollow_num());
        ArrayList<Integer> flags_im_video = md.getPosts().get(0).getFlags_im_video();
        ArrayList<String> uri_im_video = md.getPosts().get(0).getUri_im_video();
        b.putIntegerArrayList("flags_im_video",flags_im_video);
        b.putStringArrayList("uri_im_video",uri_im_video);
        b.putString("des_act",md.getPosts().get(pos).getDes_act());
        b.putFloat("per_rate",md.getPosts().get(pos).getPer_rate());
        b.putFloat("act_rate",md.getPosts().get(pos).getAct_rate());
        b.putString("label_rep",md.getPosts().get(pos).getLabel_rep());
        b.putString("label_time",md.getPosts().get(pos).getLabel_time());
        b.putDouble("lat",md.getPosts().get(pos).getMap().getLat());
        b.putDouble("log",md.getPosts().get(pos).getMap().getLon());
        b.putInt("id_act",md.getPosts().get(pos).getId_act());
        b.putBoolean("help_card",md.getPosts().get(pos).isHelp_card());
        if (md.getPosts().get(pos).isHelp_card()){
            b.putString("card_num",md.getPosts().get(pos).getCard().getCard_num());
            b.putString("name",md.getPosts().get(pos).getCard().getName());
        }
        return b;
    }
    @Override
    public void onClick(View view) {
        AppCompatDialog dc = null;
        switch (view.getId()){
            case R.id.btn_share:
                dc = new SharePostDialog(getActivity(),people,md.getPosts().get(0));
                break;
            case R.id.btn_card:
                dc = new CardDialog(getActivity(),md.getOrgans().get(0),md.getPosts().get(0).getCard());
                break;
            case R.id.btn_map:
                dc = new MapDialog(getActivity(),md.getPosts().get(0).getMap(),md.getOrgans().get(0).getUri_o(),md.getOrgans().get(0).getName_o());
                break;
            case R.id.linear_o:
                Intent intentO = new Intent(getContext(), ReligiousReadActivity.class);
                Bundle b = new Bundle();
                MainData.Organ organ = md.getOrgans().get(0);
                b.putString("id_o",organ.getId_o());
                b.putString("name_o",organ.getName_o());
                b.putString("uri_o",organ.getUri_o());
                b.putString("bio_o",organ.getBio());
                b.putBoolean("follow_o",organ.isFollow());
                b.putInt("act_num",organ.getAct_num());
                b.putInt("follow_num",organ.getFollow_num());
                intentO.putExtra("bundle",b);
                getActivity().startActivity(intentO);
                break;
            case R.id.linear_p:
                Intent intent = new Intent(getContext(), PersonReadActivity.class);
                MainData.Person person = (MainData.Person) view.getTag();
                Bundle bundle = new Bundle();
                bundle.putString("per_name",person.getName_p());
                bundle.putString("per_id",person.getId_p());
                bundle.putString("uri_im",person.getUri_p());
                bundle.putBoolean("follow",person.isFollow());
                bundle.putString("bio",person.getBio());
                intent.putExtra("bundle",bundle);
                getActivity().startActivity(intent);
                break;
        }
        if (dc != null){
            dc.show();
            dc.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    Design.noStatusColor(getActivity());
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (md == null && savedInstanceState != null && savedInstanceState.getInt("id_act",-1) != -1){
            initArgs(savedInstanceState);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState = makeBundle(md,0,type);
        super.onSaveInstanceState(outState);
    }

    class Holder {
        ImageView iv_p,iv_o;
        Button label_rep,label_time;
        TextView tv_name_p,tv_name_o,tv_des;
        RatingBar rb_act,rb_per;
        MaterialButton btn_share,btn_card,btn_map;
        LinearLayout ll_o,ll_p,ll_dot;
        ViewPager2 vp2;
        View view;
        Holder (View view){
            this.view = view;
            iv_p = view.findViewById(R.id.image_p);
            iv_o = view.findViewById(R.id.image_o);
            label_rep = view.findViewById(R.id.label_rep);
            label_time = view.findViewById(R.id.label_time);
            tv_name_p = view.findViewById(R.id.text_name_p);
            tv_name_o = view.findViewById(R.id.text_name_o);
            tv_des = view.findViewById(R.id.text_des_act);
            rb_act = view.findViewById(R.id.act_rate);
            rb_per = view.findViewById(R.id.per_rate);
            btn_share = view.findViewById(R.id.btn_share);
            btn_card = view.findViewById(R.id.btn_card);
            btn_map = view.findViewById(R.id.btn_map);
            ll_o = view.findViewById(R.id.linear_o);
            ll_p = view.findViewById(R.id.linear_p);
            ll_dot = view.findViewById(R.id.linear_dot);
            vp2 = view.findViewById(R.id.view_pager);
        }
        private void initHolder(){
            if (type == FragmentHistory.WITH_PERSON){
                ll_p.setTag(md.getPerson());
                ll_p.setOnClickListener(FragmentPost.this);
            }else {
                ll_p.setVisibility(View.GONE);
                view.invalidate();
            }
            ll_o.setOnClickListener(FragmentPost.this);
            tv_name_p.setText(md.getPerson().getName_p());
            tv_name_o.setText(md.getOrgans().get(0).getName_o());
            tv_des.setText(md.getPosts().get(0).getDes_act());
            Design.showImage(getContext(),iv_p,md.getPerson().getUri_p(),iv_p,R.drawable.person,null);
            Design.showImage(getContext(),iv_o,md.getOrgans().get(0).getUri_o(),iv_o,R.drawable.person,null);
            if (md.getPosts().get(0).getLabel_time().equals(""))
                label_time.setVisibility(View.GONE);
            else {
                label_time.setVisibility(View.VISIBLE);
                label_time.setText(md.getPosts().get(0).getLabel_time());
            }
            if (md.getPosts().get(0).getLabel_rep().equals(""))
                label_rep.setVisibility(View.GONE);
            else {
                label_rep.setVisibility(View.VISIBLE);
                label_rep.setText(md.getPosts().get(0).getLabel_rep());
            }
            rb_act.setRating(md.getPosts().get(0).getAct_rate());
            rb_per.setRating(md.getPosts().get(0).getPer_rate());
        }
    }
}