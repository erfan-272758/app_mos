package com.google.myapplication.backend;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public  class  FollowReligious implements Serializable {
    private static ArrayList<String> ids = new ArrayList<>();
    private static ArrayList<Date> times = new ArrayList<>();
    private ArrayList<String> idSave;
    private ArrayList<Date> timeSave;
    private static final String FILE_NAME = "FollowReligious/save.ss";
    public FollowReligious(){
        idSave = ids;
        timeSave = times;
    }
    private void setData(){
        ids = idSave;
        times = timeSave;
    }
    public static  ArrayList<String> getIds() {
        return ids;
    }
    public static ArrayList<Date> getTimes() {
        return times;
    }
    public static void addIdTime(String id,Date d){
        if (!ids.contains(id)){
            ids.add(id);
            times.add(d);
        }
    }
    private static void setTimes(){
        Date d = Calendar.getInstance().getTime();
        for (int i = 0; i < times.size(); i++) {
            times.set(i,d);
        }
    }
    public static void onDestroy(Context context){
        setTimes();
        File file = new File(context.getFilesDir().getAbsolutePath()+FILE_NAME);
        Log.i("write-app",file.getAbsolutePath());
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        try {
            ObjectOutputStream objectWrite = new ObjectOutputStream(new FileOutputStream(file));
            objectWrite.writeObject(new FollowReligious());
            objectWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void onCreate(Context context){
        File file = new File(context.getFilesDir().getAbsolutePath() +FILE_NAME);
        if (!file.exists())
            return;
        try {
            ObjectInputStream objectRead = new ObjectInputStream(new FileInputStream(file));
            FollowReligious follow = (FollowReligious) objectRead.readObject();
            objectRead.close();
            follow.setData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean isThereId(String id){
        return ids.contains(id);
    }
    public static void removeId(String id){
        if (ids.contains(id)){
            int index = ids.indexOf(id);
            ids.remove(index);
            times.remove(index);
        }
    }
}
