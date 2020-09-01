package com.google.myapplication.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.myapplication.R;
import com.google.myapplication.dataclass.MainData;
import com.google.myapplication.ui.Design;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentIV extends Fragment {
    String uri = "";
    int flag = -1;
    public static FragmentIV newInstance(String uri,int flag){
        FragmentIV f = new FragmentIV();
        Bundle b = new Bundle();
        b.putString("uri",uri);
        b.putInt("flag",flag);
        f.setArguments(b);
        return f;
    }
    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle b = getArguments();
        if (b != null && b.getInt("flag",-1) != -1){
            initArgs(b);
        }
        View v = inflater.inflate(R.layout.fragment_iv,container,false);
        ImageView iv = v.findViewById(R.id.image);
        PlayerView pv = v.findViewById(R.id.video);
        ProgressBar pb = v.findViewById(R.id.progress);
        //Load Image
        if (flag == MainData.FLAG_IMAGE){
            iv.setOnTouchListener(new Design.ZoomView());
            Design.showImage(getContext(),v,uri,iv,R.drawable.person,pb);
            iv.setVisibility(View.VISIBLE);
            pv.setVisibility(View.GONE);
        }
        //Load Video
        else if (flag == MainData.FLAG_VIDEO){
            Design.showVideo(getActivity(),pv,uri,true,pb);
            iv.setVisibility(View.GONE);
            pv.setVisibility(View.VISIBLE);
        }
        v.invalidate();
        return v;
    }

    private void initArgs(Bundle b) {
        uri = b.getString("uri","");
        flag = b.getInt("flag",-1);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Bundle b = new Bundle();
        b.putString("uri",uri);
        b.putInt("flag",flag);
        outState = b;
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getInt("flag",-1) != -1){
            initArgs(savedInstanceState);
        }
        super.onCreate(savedInstanceState);
    }
}
