package com.google.myapplication.backend;

import android.content.Context;
import android.util.Log;

import com.google.myapplication.dataclass.PersonInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWritePersonInfo {
    public static PersonInfo pi;
    private PersonInfo piS;
    public ReadWritePersonInfo(){
        piS = pi;
    }
    private static final String FILE_NAME = "PersonInfo/save.ss";
    public static void writePersonInfo(Context context){
        File file = new File(context.getFilesDir().getAbsolutePath()+FILE_NAME);
        Log.i("write-app",file.getAbsolutePath());
        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        try {
            ObjectOutputStream objectWrite = new ObjectOutputStream(new FileOutputStream(file));
            objectWrite.writeObject(new ReadWritePersonInfo());
            objectWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readPersonInfo(Context context){
        File file = new File(context.getFilesDir().getAbsolutePath() +FILE_NAME);
        if (!file.exists())
            return;
        try {
            ObjectInputStream objectRead = new ObjectInputStream(new FileInputStream(file));
            ReadWritePersonInfo pi = (ReadWritePersonInfo) objectRead.readObject();
            objectRead.close();
            pi.setData();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setData() {
        pi = piS;
    }
}
