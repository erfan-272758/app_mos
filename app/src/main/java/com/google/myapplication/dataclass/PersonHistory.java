package com.google.myapplication.dataclass;

import java.util.ArrayList;

public class PersonHistory {
       String per_des;
        MainData.Organ organ;
        String act_des;
        int act_id;
        float rate_act;

        public PersonHistory(String per_des, MainData.Organ organ, String act_des, int act_id, float rate_act) {
            this.per_des = per_des;
            this.organ = organ;
            this.act_des = act_des;
            this.act_id = act_id;
            this.rate_act = rate_act;
        }

        public float getRate_act() {
            return rate_act;
        }

        public int getAct_id() {
            return act_id;
        }

        public MainData.Organ getOrgan() {
            return organ;
        }

        public String getAct_des() {
            return act_des;
        }

        public String getPer_des() {
            return per_des;
        }
    }
