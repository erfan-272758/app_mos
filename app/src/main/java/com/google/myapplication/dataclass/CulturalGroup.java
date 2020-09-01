package com.google.myapplication.dataclass;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class CulturalGroup extends Religious {
    String bossName = "";
    String parent = "";
    String userConditional = "";
    String history = "";
    ArrayList<Class> classes;
    public CulturalGroup(MainData.Post.Map map, String bossName) {
        super(map,CULTURAL);
        this.bossName = bossName;
        classes = new ArrayList<>();
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public String getUserConditional() {
        return userConditional;
    }

    public void setUserConditional(String userConditional) {
        this.userConditional = userConditional;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBossName() {
        return bossName;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }
    public void fillFromClass(ArrayList<String> names,ArrayList<String> managers,ArrayList<String> times){
        for (Class node:classes) {
            names.add(node.getName());
            managers.add(node.getManager());
            times.add(node.getTime());
        }
    }
    public static ArrayList<Class> makeClass(ArrayList<String> names,ArrayList<String> managers,ArrayList<String> times){
        ArrayList<Class> node = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Class c = new Class(names.get(i),managers.get(i),times.get(i));
            node.add(c);
        }
        return node;
    }
    public static class Class{
        String name;
        String manager;
        String time;

        public Class(String name, String manager, String time) {
            this.name = name;
            this.manager = manager;
            this.time = time;
        }

        public String getManager() {
            return manager;
        }

        public String getTime() {
            return time;
        }

        public void setManager(String manager) {
            this.manager = manager;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
