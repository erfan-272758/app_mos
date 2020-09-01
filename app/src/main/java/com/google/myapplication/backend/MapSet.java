package com.google.myapplication.backend;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class MapSet implements OnMapReadyCallback {
    double lat, lon;
    String name;
    Bitmap bit;
    Context context;
    DisplayMetrics display;
    public MapSet(double lat, double lon, String name, Bitmap bit, FragmentActivity act) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.bit = bit;
        this.context = act;
        this.display = findDisplay(act);
    }

    public void setMap(SupportMapFragment smf) {
        if (smf!=null){
            Log.i("my-map","Not NULL");
            smf.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng pos = new LatLng(lat, lon);
        Marker marker = map.addMarker(new MarkerOptions().position(pos));
        marker.setTitle(name);
        marker.setDraggable(false);
        if (bit != null) {
            int width = bit.getWidth();
            int height = bit.getHeight();
            if (((float)width) / display.density > 35){
                height = (int) (35 * display.density / ((float)width / (float)height));
                width = (int) (35 * display.density);
            }
            Bitmap result = Bitmap.createScaledBitmap(bit,width,height,false);
            BitmapDescriptor bc = BitmapDescriptorFactory.fromBitmap(result);
            marker.setIcon(bc);
        }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pos,15));
    }
    public static DisplayMetrics findDisplay(FragmentActivity act){
        DisplayMetrics metrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
