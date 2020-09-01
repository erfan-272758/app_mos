package com.google.myapplication.fragments.religiousread;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.myapplication.R;
import com.google.myapplication.backend.MapSet;
import com.google.myapplication.backend.PrepareData;
import com.google.myapplication.dataclass.CulturalGroup;
import com.google.myapplication.dataclass.Heiat;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.dataclass.Mosque;
import com.google.myapplication.dataclass.Religious;
import com.google.myapplication.pageradapter.PAIV;
import com.google.myapplication.recycleradapter.RAPrivateInfo;
import com.google.myapplication.ui.Design;
import com.google.myapplication.ui.Scroll;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class FragmentReligiousOutline extends Fragment implements View.OnTouchListener, View.OnClickListener {
    Religious relig = null;
    String org_id,uri_im,name_o;
    Holder holder;
    public static FragmentReligiousOutline newInstance(String org_id,String uri_im,String name_o){
        FragmentReligiousOutline f = new FragmentReligiousOutline();
        f.setArguments(f.writeIntoBundle(null,org_id,uri_im,name_o));
        return f;
    }

    private void readFromBundle(Bundle b){
        org_id = b.getString("org_id","");
        name_o = b.getString("name_o","");
        uri_im = b.getString("uri_im","");
        String address = b.getString("address","");
        if (address.isEmpty())
            return;
        String phone_num = b.getString("phone_num","");
        String connection_way = b.getString("connection_way","");
        String outline_info = b.getString("outline_info","");
        double lat = b.getDouble("lat",0);
        double lon = b.getDouble("lon",0);
        MainData.Post.Map map = new MainData.Post.Map(lat,lon);
        ArrayList<String> uri_im_video = b.getStringArrayList("uri_im_video");
        ArrayList<Integer> flag_im_video = b.getIntegerArrayList("flag_im_video");
        ArrayList<String> names = b.getStringArrayList("field_name");
        ArrayList<String> deses = b.getStringArrayList("field_des");
        ArrayList<Religious.Fields> fields = Religious.makeFields(names,deses);
        int type = b.getInt("type",relig.getType());
        switch (type){
            case Religious.MOSQUE:
                readMosque(map,b);
                break;
            case Religious.HEIAT:
                readHeiat(map,b);
                break;
            case Religious.CULTURAL:
            case Religious.QURAN:
                readCultural(map,b);
                break;
        }
        relig.setAddress(address);
        relig.setConnectionWay(connection_way);
        relig.setOutlineInfo(outline_info);
        relig.setPhoneNum(phone_num);
        relig.setUri_im_video(uri_im_video);
        relig.setFlags_im_video(flag_im_video);
        relig.getFields().addAll(fields);
    }
    private void readMosque(MainData.Post.Map map, Bundle b) {
        String emam_name = b.getString("emam_name","");
        String children = b.getString("children","");
        boolean pray_m = b.getBoolean("pray_m",false);
        boolean pray_z = b.getBoolean("pray_z",false);
        boolean pray_s = b.getBoolean("pray_s",false);
        boolean has_library = b.getBoolean("has_library",false);
        int empty_library = b.getInt("empty_library",0);
        relig = new Mosque(map,emam_name);
        Mosque mos = (Mosque) relig;
        mos.setChildren(children);
        mos.setPray_m(pray_m);
        mos.setPray_s(pray_s);
        mos.setPray_z(pray_z);
        mos.setHasLibrary(has_library);
        mos.setLibEmpty(empty_library);
    }
    private void readHeiat(MainData.Post.Map map, Bundle b) {
        String boss_name = b.getString("boss_name","");
        String children = b.getString("children","");
        String madahs_name = b.getString("madahs_name","");
        String roozehs_name = b.getString("roozehs_name","");
        String speakers_name = b.getString("speakers_name","");
        relig = new Heiat(map,boss_name);
        Heiat hei = (Heiat) relig;
        hei.setChildren(children);
        hei.setMadahsName(madahs_name);
        hei.setRoozehsName(roozehs_name);
        hei.setSpeakersName(speakers_name);
    }
    private void readCultural(MainData.Post.Map map, Bundle b) {
        String boss_name = b.getString("boss_name","");
        String history = b.getString("history","");
        String parent = b.getString("parent","");
        String user_con = b.getString("user_con","");
        ArrayList<String> names = b.getStringArrayList("class_name");
        ArrayList<String> managers = b.getStringArrayList("class_manager");
        ArrayList<String> times = b.getStringArrayList("class_time");
        ArrayList<CulturalGroup.Class> classes = CulturalGroup.makeClass(names,managers,times);
        relig = new CulturalGroup(map,boss_name);
        CulturalGroup cul = (CulturalGroup) relig;
        cul.setHistory(history);
        cul.setParent(parent);
        cul.setUserConditional(user_con);
        cul.getClasses().addAll(classes);
    }

    private Bundle writeIntoBundle(@Nullable Religious relig, String org_id,String uri_im,String name_o) {
        Bundle b = new Bundle();
        b.putString("org_id",org_id);
        b.putString("name_o",name_o);
        b.putString("uri_im",uri_im);
        if (relig == null)
            return b;
        b.putString("address",relig.getAddress());
        b.putString("phone_num",relig.getPhoneNum());
        b.putString("connection_way",relig.getConnectionWay());
        b.putString("outline_info",relig.getOutlineInfo());
        b.putDouble("lat",relig.getMap().getLat());
        b.putDouble("lon",relig.getMap().getLon());
        b.putStringArrayList("uri_im_video",relig.getUri_im_video());
        b.putIntegerArrayList("flag_im_video",relig.getFlags_im_video());
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> deses = new ArrayList<>();
        relig.fillFromFields(names,deses);
        b.putStringArrayList("field_name",names);
        b.putStringArrayList("field_des",deses);
        b.putInt("type",relig.getType());
        switch (relig.getType()){
            case Religious.MOSQUE:
                writeMosque(b,(Mosque)relig);
                break;
            case Religious.HEIAT:
                writeHeiat(b,(Heiat)relig);
                break;
            case Religious.CULTURAL:
            case Religious.QURAN:
                writeCultural(b, (CulturalGroup)relig);
                break;

        }
        return b;
    }
    private void writeCultural(Bundle b, CulturalGroup cul) {
        b.putString("boss_name",cul.getBossName());
        b.putString("history",cul.getHistory());
        b.putString("parent",cul.getParent());
        b.putString("user_con",cul.getUserConditional());
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> managers = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        cul.fillFromClass(names,managers,times);
        b.putStringArrayList("class_name",names);
        b.putStringArrayList("class_manager",managers);
        b.putStringArrayList("class_time",times);
    }
    private void writeHeiat(Bundle b, Heiat hei) {
        b.putString("boss_name",hei.getBossName());
        b.putString("children",hei.getChildren());
        b.putString("madahs_name",hei.getMadahsName());
        b.putString("roozehs_name",hei.getRoozehsName());
        b.putString("speakers_name",hei.getSpeakersName());
    }
    private void writeMosque(Bundle b, Mosque mos) {
        b.putString("emam_name",mos.getEmam_name());
        b.putString("children",mos.getChildren());
        b.putBoolean("pray_m",mos.isPray_m());
        b.putBoolean("pray_z",mos.isPray_z());
        b.putBoolean("pray_s",mos.isPray_s());
        b.putBoolean("has_library",mos.isHasLibrary());
        b.putInt("empty_library",mos.getLibEmpty());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (relig == null){
            relig = PrepareData.getOrganOutline(org_id);
        }
        View view = inflater.inflate(R.layout.religious_read_outline,container,false);
        holder = new Holder(view);
        //View Pager
        holder.vp.setAdapter(holder.adapter);
        Design.createDots(getActivity(),holder.linear,holder.vp.getAdapter().getItemCount());
        holder.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Design.updateDots(getActivity(),holder.linear,position);
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });
        //Recycler View
        holder.rv.setLayoutManager(holder.lm);
        holder.rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        holder.rv.setAdapter(holder.r_adapter);
        //Fragment
        MapSet ms = new MapSet(relig.getMap().getLat(),relig.getMap().getLon()
                ,name_o,Design.getBitmapFromURL(getContext(),uri_im),getActivity());
        ms.setMap(holder.mapF);
        //View
        holder.cover.setOnTouchListener(this);
        holder.cover2.setOnTouchListener(this);
        //Text View
        holder.tv_info.setText(relig.getOutlineInfo());
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i("abcc",(motionEvent.getAction())+"");
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                if (view.getId() == R.id.cover)
                    holder.scroll.setEnable(false);
                if (view.getId() == R.id.cover2)
                    holder.scroll.setEnable(true);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        Log.i("abcc","click");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            readFromBundle(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState = writeIntoBundle(relig,org_id,uri_im,name_o);
        super.onSaveInstanceState(outState);
    }

    class Holder{
        ViewPager2 vp;
        LinearLayout linear;
        PAIV adapter;
        RecyclerView rv;
        RAPrivateInfo r_adapter;
        LinearLayoutManager lm;
        SupportMapFragment mapF;
        Scroll scroll;
        View cover,cover2;
        TextView tv_info;
        Holder(View view){
            vp = view.findViewById(R.id.view_pager);
            linear = view.findViewById(R.id.linear);
            adapter = new PAIV(getActivity(),relig.getUri_im_video(),relig.getFlags_im_video());
            rv = view.findViewById(R.id.re_view);
            r_adapter= new RAPrivateInfo(getContext(),relig);
            lm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mapF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            scroll = view.findViewById(R.id.scroll);
            cover = view.findViewById(R.id.cover);
            cover2 = view.findViewById(R.id.cover2);
            tv_info = view.findViewById(R.id.text_info);
        }
    }
}
