package com.google.myapplication.activity;

import android.content.Context;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.myapplication.R;
import com.google.myapplication.backend.MapSet;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.ui.Design;

import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MapDialog extends AppCompatDialog {
    public MapDialog(FragmentActivity context, MainData.Post.Map map, String uri,String name) {
        super(context, R.style.DialogStyle);
        setContentView(R.layout.dialog_map);
        SupportMapFragment fragment = FragmentManager.findFragment(findViewById(R.id.map));
        MapSet ms = new MapSet(map.getLat(),map.getLon(),name, Design.getBitmapFromURL(context,uri),context);
        ms.setMap(fragment);
    }
}
