package com.google.myapplication.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.myapplication.R;
import com.google.myapplication.ui.ValueSelector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.SwitchCompat;

public class SearchDialog extends AppCompatDialog {
    AppCompatRadioButton radio_m,radio_q,radio_h,radio_c;
    RelativeLayout rl_m,rl_c,rl_q,rl_h;
    SwitchCompat sc_gps,sc_has_lib,sc_par;
    ValueSelector vs;
    AppCompatCheckBox ch_m_s,ch_m_z,ch_m_m;
    MaterialButton btn_conf;

    public SearchDialog(Context context) {
        super(context,R.style.DialogStyle);
        setContentView(R.layout.dialog_shearch_more);
        radio_m = findViewById(R.id.radio_m);
        rl_m = findViewById(R.id.relative_m);
        sc_gps = findViewById(R.id.ch_gps);
        sc_has_lib = findViewById(R.id.ch_has_lib);
        sc_par = findViewById(R.id.ch_par);
        vs = findViewById(R.id.value_selector);
        ch_m_m = findViewById(R.id.ch_par_m);
        ch_m_s = findViewById(R.id.ch_par_s);
        ch_m_z = findViewById(R.id.ch_par_z);
        btn_conf = findViewById(R.id.btn_conf);
        radio_m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    rl_m.setVisibility(View.VISIBLE);
                }else {
                    rl_m.setVisibility(View.GONE);
                }
            }
        });
        sc_has_lib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    vs.setEnable(true);
                }else {
                    vs.setEnable(false);
                }
            }
        });
        sc_par.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    ch_m_m.setEnabled(true);
                    ch_m_s.setEnabled(true);
                    ch_m_z.setEnabled(true);
                }else {
                    ch_m_m.setEnabled(false);
                    ch_m_s.setEnabled(false);
                    ch_m_z.setEnabled(false);
                }
            }
        });
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
       }

}
