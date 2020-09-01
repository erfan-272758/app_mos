package com.google.myapplication.dataclass;

import androidx.annotation.NonNull;

public class Mosque extends Religious {

    boolean hasLibrary = false;
    int libEmpty = 0;
    String emam_name ="";
    boolean pray_s = false;
    boolean pray_z = false;
    boolean pray_m = false;
    String children = "";

    public Mosque(MainData.Post.Map map, String emam_name) {
        super(map,MOSQUE);
        this.emam_name = emam_name;
    }

    public boolean isPray_s() {
        return pray_s;
    }

    public void setPray_s(boolean pray_s) {
        this.pray_s = pray_s;
    }

    public boolean isPray_z() {
        return pray_z;
    }

    public void setPray_z(boolean pray_z) {
        this.pray_z = pray_z;
    }

    public boolean isPray_m() {
        return pray_m;
    }

    public void setPray_m(boolean pray_m) {
        this.pray_m = pray_m;
    }

    public void setEmam_name(String emam_name) {
        this.emam_name = emam_name;
    }

    public String getEmam_name() {
        return emam_name;
    }

    public boolean isHasLibrary() {
        return hasLibrary;
    }

    public void setHasLibrary(boolean hasLibrary) {
        this.hasLibrary = hasLibrary;
    }

    public int getLibEmpty() {
        return libEmpty;
    }

    public void setLibEmpty(int libEmpty) {
        this.libEmpty = libEmpty;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}
