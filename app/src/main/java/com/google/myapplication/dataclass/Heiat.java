package com.google.myapplication.dataclass;

import androidx.annotation.NonNull;

public class Heiat extends Religious {
    String bossName = "";
    String madahsName = "";
    String roozehsName = "";
    String speakersName = "";
    String children ="";

    public Heiat(MainData.Post.Map map, String bossName) {
        super(map,HEIAT);
        this.bossName = bossName;
    }


    public String getMadahsName() {
        return madahsName;
    }

    public void setMadahsName(String madahsName) {
        this.madahsName = madahsName;
    }

    public String getRoozehsName() {
        return roozehsName;
    }

    public void setRoozehsName(String roozehsName) {
        this.roozehsName = roozehsName;
    }

    public String getSpeakersName() {
        return speakersName;
    }

    public void setSpeakersName(String speakersName) {
        this.speakersName = speakersName;
    }

    public String getBossName() {
        return bossName;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }
}
