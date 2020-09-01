package com.google.myapplication.backend;

import android.content.Context;
import android.widget.ProgressBar;

import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.dataclass.Mosque;
import com.google.myapplication.dataclass.PersonHistory;
import com.google.myapplication.dataclass.Religious;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PrepareData {
    private static Random random = new Random();
    private static String[] strings = new String[]{
            "https://www.eligasht.com/Blog/wp-content/uploads/2018/11/5-39.jpg",
            "https://safarzon.com/mag/wp-content/uploads/2019/03/%DA%AF%D9%88%D9%87%D8%B1%D8%B4%D8%A7%D8%AF1.jpg",
            "https://safarzon.com/mag/wp-content/uploads/2019/03/%DA%AF%D9%88%D9%87%D8%B1%D8%B4%D8%A7%D8%AF3.jpg",
            "https://safarzon.com/mag/wp-content/uploads/2019/03/%DA%AF%D9%88%D9%87%D8%B1%D8%B4%D8%A7%D8%AF5-1.jpg",
            "https://safarzon.com/mag/wp-content/uploads/2019/03/%D8%B4%D8%A8%D8%B3%D8%AA%D8%A7%D9%86%E2%80%8C%D9%87%D8%A7%DB%8C-%DA%AF%D9%88%D9%87%D8%B1%D8%B4%D8%A7%D8%AF.jpg"};
    private static String[] vs = new String[]{
            "https://hw20.cdn.asset.aparat.com/aparat-video/2b1e933b2f5c1ffacdd9b124d015ea7e24333471-360p.mp4",
            "https://hw1.cdn.asset.aparat.com/aparat-video/dc89da044c27c1d5636eaf26fa7ffbd324289476-360p.mp4",
            "https://hw18.cdn.asset.aparat.com/aparat-video/59323944c7ce5d152be228a8f04f182524322623-360p.mp4",
            "https://hw7.cdn.asset.aparat.com/aparat-video/efa63a3dbad2da6fff7e39e1210e7fd524315418-360p.mp4"};
    //fill person,organ
    public static ArrayList<MainData> getNewSuggest(){
        return new ArrayList<>();
    }
    public static ArrayList<MainData> getNewSuggest(ProgressBar pb){
        return new ArrayList<>();
    }
    public static ArrayList<MainData> getExtraSuggest(ProgressBar pb,int size){
        return new ArrayList<>();
    }
    public static ArrayList<MainData> getSearchPerson_Suggest(String idOrPhone){
        return new ArrayList<>();
    }
    //fill person
    public static ArrayList<MainData.Person> getNewSuggestWO(ProgressBar pb){
        return new ArrayList<>();
    }
    public static ArrayList<MainData.Person> getSearchPerson(String idOrPhone){
        return new ArrayList<>();
    }
    //fill post
    synchronized public static void fillPost(MainData.Post post){
        if (!post.isRead()){
            //Load Data


            //if load successful
            post.setRead(true);
        }
    }
    //send suggest
    public static void sendSuggest(Context context,String per_id,int act_id,String per_des,String act_des,float act_rate){
    }
    public static void sendSuggestForAll(Context context,int act_id,String per_des,String act_des,float act_rate){
    }
    public static float changePersonRate(float per_rate,int act_id){
        return per_rate;
    }
    //get history of suggest C
    public static ArrayList<PersonHistory> getPersonHistory(String per_id,@Nullable ProgressBar pb){
        return new ArrayList<>();
    }
    //extra history of suggest C
    public static ArrayList<PersonHistory> extraPersonHistory(String per_id,@NonNull ProgressBar pb,int size){
        return new ArrayList<>();
    }

    //change follow person
    public static void changeFollowPerson(String per_id,boolean f){}
    //get list of organ C
    public static ArrayList<MainData.Organ> getOrganFollowing(String per_id,@Nullable ProgressBar pb){
        return new ArrayList<>();
    }
    //extra list of organ C
    public static ArrayList<MainData.Organ> extraOrganFollowing(String per_id,@NonNull ProgressBar pb,int size){
        return new ArrayList<>();
    }
    //fill posts of organ
    public static ArrayList<MainData.Post> getOrganPosts(String id_o,@Nullable ProgressBar pb){
        return new ArrayList<>();
    }
    public static ArrayList<MainData.Post> extraOrganPosts(String id_o,@NonNull ProgressBar pb,int size){
        return new ArrayList<>();
    }
    //change follow organ
    public static void changeFollowOrgan(String org_id,boolean f){}
    //fill outline of organ
    public static Religious getOrganOutline(String id_o){
        return null;
    }

}
