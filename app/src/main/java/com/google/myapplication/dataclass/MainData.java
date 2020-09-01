package com.google.myapplication.dataclass;

import java.util.ArrayList;
import java.util.HashMap;

public class MainData {
    public static final int FLAG_IMAGE = 0;
    public static final int FLAG_VIDEO = 1;

    Person person = null;
    ArrayList<Organ> organs = null;
    ArrayList<String>describe_p = null;
    ArrayList<Post> posts = null;
    public static class Person{
        String name_p;
        String uri_p;
        String id_p;
        String bio;
        boolean read = false;
        boolean follow;
        public Person(String name_p,String bio ,String uri_p, String id_p,boolean follow) {
            this.name_p = name_p;
            this.uri_p = uri_p;
            this.id_p = id_p;
            this.follow = follow;
            this.bio = bio;
        }

        public String getBio() {
            return bio;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public boolean isFollow() {
            return follow;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public String getId_p() {
            return id_p;
        }

        public String getName_p() {
            return name_p;
        }

        public String getUri_p() {
            return uri_p;
        }

        public void setId_p(String id_p) {
            this.id_p = id_p;
        }

        public void setName_p(String name_p) {
            this.name_p = name_p;
        }

        public void setUri_p(String uri_p) {
            this.uri_p = uri_p;
        }
    }
    public static class Organ{
        String name_o;
        String uri_o;
        String id_o;
        String bio;
        boolean follow;
        int follow_num;
        int act_num;
        int type;
        Religious religious;
        ArrayList<Post> posts;
        boolean read = false;

        public Organ(String name_o, String uri_o, String id_o,boolean follow,String bio,int act_num,int follow_num) {
            this.name_o = name_o;
            this.uri_o = uri_o;
            this.id_o = id_o;
            this.follow = follow;
            this.bio = bio;
            this.act_num = act_num;
            this.follow_num = follow_num;
        }

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public int getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(int follow_num) {
            this.follow_num = follow_num;
        }

        public int getAct_num() {
            return act_num;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Religious getReligious() {
            return religious;
        }

        public void setReligious(Religious religious) {
            this.religious = religious;
        }

        public ArrayList<Post> getPosts() {
            return posts;
        }

        public void setPosts(ArrayList<Post> posts) {
            this.posts = posts;
        }

        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getName_o() {
            return name_o;
        }

        public void setName_o(String name_o) {
            this.name_o = name_o;
        }

        public String getUri_o() {
            return uri_o;
        }

        public void setUri_o(String uri_o) {
            this.uri_o = uri_o;
        }

        public String getId_o() {
            return id_o;
        }

        public void setId_o(String id_o) {
            this.id_o = id_o;
        }
    }
    public static class Post{
        boolean seen;
        int id_act;
        ArrayList<String> uri_im_video;
        ArrayList<Integer> flags_im_video;
        String des_act;
        Card card;
        Map map;
        float act_rate;
        float per_rate;
        String label_rep,label_time;
        boolean help_card;

        public boolean isHelp_card() {
            return help_card;
        }

        public void setHelp_card(boolean help_card) {
            this.help_card = help_card;
        }

        public static class Map{
            double lat,lon;

            public Map(double lat, double lon) {
                this.lat = lat;
                this.lon = lon;
            }

            public double getLon() {
                return lon;
            }

            public double getLat() {
                return lat;
            }
        }
        public static class Card{
            String name;
            String card_num;

            public Card(String name, String card_num) {
                this.name = name;
                this.card_num = card_num;
            }

            public String getCard_num() {
                return card_num;
            }
            public String getName() {
                return name;
            }

        }
        boolean read = false;
        public Post(int id_act, ArrayList<String> uri_im_video,ArrayList<Integer> flags_im_video, String des_act,
                    float act_rate, float per_rate, String label_rep, String label_time) {
            this.id_act = id_act;
            this.uri_im_video = uri_im_video;
            this.flags_im_video = flags_im_video;
            this.des_act = des_act;
            this.act_rate = act_rate;
            this.per_rate = per_rate;
            this.label_rep = label_rep;
            this.label_time = label_time;
        }
        public boolean isRead() {
            return read;
        }

        public void setRead(boolean read) {
            this.read = read;
        }


        public boolean isSeen() {
            return seen;
        }

        public void setSeen(boolean seen) {
            this.seen = seen;
        }

        public int getId_act() {
            return id_act;
        }

        public void setId_act(int id_act) {
            this.id_act = id_act;
        }

        public ArrayList<Integer> getFlags_im_video() {
            return flags_im_video;
        }

        public ArrayList<String> getUri_im_video() {
            return uri_im_video;
        }

        public void setFlags_im_video(ArrayList<Integer> flags_im_video) {
            this.flags_im_video = flags_im_video;
        }

        public void setUri_im_video(ArrayList<String> uri_im_video) {
            this.uri_im_video = uri_im_video;
        }

        public String getDes_act() {
            return des_act;
        }

        public void setDes_act(String des_act) {
            this.des_act = des_act;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }

        public float getAct_rate() {
            return act_rate;
        }

        public void setAct_rate(float act_rate) {
            this.act_rate = act_rate;
        }

        public float getPer_rate() {
            return per_rate;
        }

        public void setPer_rate(float per_rate) {
            this.per_rate = per_rate;
        }

        public String getLabel_rep() {
            return label_rep;
        }

        public void setLabel_rep(String label_rep) {
            this.label_rep = label_rep;
        }

        public String getLabel_time() {
            return label_time;
        }

        public void setLabel_time(String label_time) {
            this.label_time = label_time;
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Organ> getOrgans() {
        return organs;
    }

    public void setOrgans(ArrayList<Organ> organs) {
        this.organs = organs;
    }

    public ArrayList<String> getDescribe_p() {
        return describe_p;
    }

    public void setDescribe_p(ArrayList<String> describe_p) {
        this.describe_p = describe_p;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
    public interface Data{
        ArrayList<MainData> getMainData();
        ArrayList<MainData.Person> getListPerson();
    }

}
