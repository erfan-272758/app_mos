package com.google.myapplication.dataclass;

import java.util.ArrayList;

public class Religious {
    public static final int MOSQUE = 0;
    public static final int HEIAT = 1;
    public static final int QURAN = 2;
    public static final int CULTURAL = 3;
    MainData.Post.Map map;
    String address;
    String phoneNum;
    String connectionWay;
    ArrayList<String> uri_im_video;
    ArrayList<Integer> flags_im_video;
    String outlineInfo;
    ArrayList<Fields> fields;
    int type;
    public Religious(MainData.Post.Map map,int type) {
        this.map = map;
        fields = new ArrayList<>();
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void fillFromFields(ArrayList<String> names,ArrayList<String> des){
        for (Fields field:fields) {
            names.add(field.name);
            des.add(field.des);
        }
    }
    public static ArrayList<Fields> makeFields(ArrayList<String> names,ArrayList<String> des){
        ArrayList<Fields> fields = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Fields field = new Fields(names.get(i),des.get(i));
            fields.add(field);
        }
        return fields;
    }

    public ArrayList<Fields> getFields() {
        return fields;
    }

    public MainData.Post.Map getMap() {
        return map;
    }

    public void setMap(MainData.Post.Map map) {
        this.map = map;
    }

    public String getConnectionWay() {
        return connectionWay;
    }

    public void setConnectionWay(String connectionWay) {
        this.connectionWay = connectionWay;
    }

    public ArrayList<String> getUri_im_video() {
        return uri_im_video;
    }

    public void setUri_im_video(ArrayList<String> uri_im_video) {
        this.uri_im_video = uri_im_video;
    }

    public ArrayList<Integer> getFlags_im_video() {
        return flags_im_video;
    }

    public void setFlags_im_video(ArrayList<Integer> flags_im_video) {
        this.flags_im_video = flags_im_video;
    }

    public String getOutlineInfo() {
        return outlineInfo;
    }

    public void setOutlineInfo(String outlineInfo) {
        this.outlineInfo = outlineInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public static class Fields{
        String name;
        String des;

        public Fields(String name, String des) {
            this.name = name;
            this.des = des;
        }

        public String getName() {
            return name;
        }

        public String getDes() {
            return des;
        }
    }
}
