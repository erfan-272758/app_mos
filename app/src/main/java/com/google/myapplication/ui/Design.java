package com.google.myapplication.ui;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
import com.google.android.material.tabs.TabLayout;
import com.google.myapplication.R;
import com.google.myapplication.activity.PersianActivity;

import java.security.MessageDigest;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class Design {
    public static void createDots(Context context, ViewGroup vg, int n){
        if (n < 2)
            return;
        vg.removeAllViewsInLayout();
        for (int i = 0; i < n; i++) {
            TextView tv = new TextView(context);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv.setText(Html.fromHtml("&#8226;",Html.FROM_HTML_MODE_COMPACT));
                tv.setTextColor(context.getResources().getColor(R.color.colorDotsOut,null));
            }else {
                tv.setText(Html.fromHtml("&#8226;"));
                tv.setTextColor(context.getResources().getColor(R.color.colorDotsOut));
            }
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
            vg.addView(tv);
        }
    }
    public static void updateDots(Context context,ViewGroup vg,int pos){
        if (vg.getChildCount() < 2)
            return;
        TextView tv = (TextView) vg.getChildAt(pos);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(context.getResources().getColor(R.color.colorDotsIn,null));
        }else {
            tv.setTextColor(context.getResources().getColor(R.color.colorDotsIn));
        }
        for (int i = 0; i < vg.getChildCount(); i++) {
            if (i != pos)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ((TextView)(vg.getChildAt(i))).setTextColor(context.getResources().getColor(R.color.colorDotsOut,null));
                }else {
                    ((TextView)(vg.getChildAt(i))).setTextColor(context.getResources().getColor(R.color.colorDotsOut));
                }
        }
    }
    public static void noStatusColor (FragmentActivity act){
        Window w = act.getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.setStatusBarColor(Color.TRANSPARENT);
    }
    public static void setViewPager(ViewPager vp, PagerAdapter adapter, TabLayout tl,int[] iconIds){
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
        for (int i = 0; i < tl.getTabCount(); i++) {
            tl.getTabAt(i).setIcon(iconIds[i]);
            if (i == 0)
                tl.getTabAt(i).getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            else
                tl.getTabAt(i).getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public static void setViewPager2(final ViewPager2 vp2, FragmentStateAdapter adapter, TabLayout tl, int[] iconIds){
        vp2.setAdapter(adapter);
        for (int position = 0; position < adapter.getItemCount(); position++) {
            TabLayout.Tab tab = tl.newTab();
            tl.addTab(tab);
            tab.setIcon(iconIds[position]);
            if (position == 0)
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
            else
                tab.getIcon().setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY);
        }
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                vp2.setCurrentItem(tab.getPosition(),true);
                Log.i("check1","onTabSelected "+vp2.getCurrentItem());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("check1","onTabUnselected");
                tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("check1","onTabReselected "+tab.getPosition());
            }
        });

    }
    public static class ZoomView implements View.OnTouchListener {
        private static final String TAG = "Touch";
        @SuppressWarnings("unused")
        private static final long DOUBLE_CLICK_MAX_DELAY = 200L;

        // These matrices will be used to scale points of the image
        // The 3 states (events) which the user is trying to perform
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 2;
        private long oldTime = 0;
        int mode = NONE;

        // these PointF objects are used to record the point(s) the user is touching
        float[] start = new float[]{0,0};
        float oldDist = 1f;
        boolean doubleC = false;

        /** Called when the activity is first created. */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            ImageView view = (ImageView) v;
            float scale;

            // Handle touch events here...

            switch (event.getAction()& MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_UP: // first finger lifted

                case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                    mode = NONE;
                    Log.d(TAG, "mode=NONE");
                    break;

                case MotionEvent.ACTION_DOWN:   // first finger down only
                    if (oldTime == 0)
                        oldTime = System.currentTimeMillis();
                    else {
                        long newTime = System.currentTimeMillis();
                        if (newTime - oldTime <= DOUBLE_CLICK_MAX_DELAY){
                            if (doubleC){
                                doubleC = false;
                                view.animate()
                                        .translationY(0)
                                        .translationX(0)
                                        .scaleY(1)
                                        .scaleX(1)
                                        .setDuration(250)
                                        .start();

                            }else {
                                doubleC = true;
                                view.animate()
                                        .translationY(0)
                                        .translationX(0)
                                        .scaleY(2.5f)
                                        .scaleX(2.5f)
                                        .setDuration(250)
                                        .start();
                            }
                        }
                        oldTime = newTime;
                    }
                    start[0] =event.getX();
                    start[1] = event.getY();
                    Log.d(TAG, "mode=DRAG"); // write to LogCat
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.d(TAG, "mode=ZOOM1");
                    if (event.getPointerCount() == 2) {
                        oldDist = spacing(event);
                        Log.d(TAG, "oldDist=" + oldDist);
                        if (oldDist > 5f) {
                            mode = ZOOM;
                            Log.d(TAG, "mode=ZOOM");
                        }
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == ZOOM)
                    {
                        view.setTranslationX(event.getX() - start[0] + view.getTranslationX());
                        view.setTranslationY(event.getY() - start[1]+view.getTranslationY());
                        float newDist = spacing(event);
                        Log.d(TAG, "newDist=" + newDist);
                        if (newDist > 5f)
                        {
                            scale = newDist / oldDist; // setting the scaling of the
                            scale *= view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                    }
                    break;
            }

            return true; // indicate event was handled
        }

        private float spacing(MotionEvent event)
        {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }
      }
    public static void showImage(final Context context, final View v , String uri, final ImageView im,
                                 @DrawableRes int idErrorLoad , @Nullable final ProgressBar pb){
        if (pb!= null)
            pb.setVisibility(View.VISIBLE);
        v.invalidate();
        Glide.with(context)
                .asBitmap()
                .load(Uri.parse(uri))
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .addListener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if (pb!=null)
                            pb.setVisibility(View.GONE);
                        v.invalidate();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (pb!=null){
                            pb.setVisibility(View.GONE);
                            v.invalidate();
                        }
                        Log.i("my-data","onReady");
                        return false;
                    }
                })
                .error(idErrorLoad)
                .into(im);
        v.invalidate();
    }
    public static void showVideo(final Context context, final PlayerView video, String uri, boolean start, final ProgressBar pb){
        TrackSelector trackSelectorDef = new DefaultTrackSelector();
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context,trackSelectorDef);
        com.google.android.exoplayer2.upstream.DataSource.Factory factory = new DefaultDataSourceFactory(context, Util.getUserAgent(context,context.getString(R.string.app_name)));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(Uri.parse(uri));
        player.prepare(videoSource);
        player.setPlayWhenReady(start);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                pb.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_READY){
                    pb.setVisibility(View.GONE);
                    if (playWhenReady){
                        Log.i("my-video","here");
                        ((PersianActivity)(context)).pauseLastVideo(video);
                        ((PersianActivity)(context)).setLastVideo(video);
                    }
                }
                if (playbackState == (Player.STATE_IDLE | Player.STATE_BUFFERING)){
                    pb.setVisibility(View.VISIBLE);
                  }
            }
        }); video.setPlayer(player);

    }

    @SuppressLint("CheckResult")
    public static Bitmap getBitmapFromURL(Context context, String uri){
        if (uri.isEmpty()){
            @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bd = (BitmapDrawable) context.getDrawable(R.drawable.person);
            assert bd != null;
            return bd.getBitmap();
        }
        final Bitmap[] bitmap = new Bitmap[1];
        Glide.with(context)
                .asBitmap()
                .load(Uri.parse(uri))
                .thumbnail(0.5f)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .addListener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        bitmap[0] = null;
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        bitmap[0] = resource;
                        return false;
                    }
                }).error(R.drawable.person);
        if (bitmap[0] == null){
            BitmapDrawable bd = (BitmapDrawable) context.getDrawable(R.drawable.person);
            return bd.getBitmap();
        }
        return bitmap[0];
    }

    public interface MainAct{
        void requestDraw();
        void requestViewPager(int pos);
    }

//    public static void makeNotify(Context context, Class classNext, String title, String short_text, String long_text){
//        BitmapDrawable bd = (BitmapDrawable) context.getDrawable(R.drawable.mosque3);
//        Intent intent = new Intent(context,classNext);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pi = PendingIntent.getActivity(context,0,intent,0);
//        NotificationChannel channel = new NotificationChannel("notify-id","Notifications", NotificationManager.IMPORTANCE_DEFAULT);
//        channel.setDescription("notification for update data");
//        channel.setVibrationPattern(new long[]{200,200,200,200,200});
//        NotificationManager manager = context.getSystemService(NotificationManager.class);
//        manager.createNotificationChannel(channel);
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channel.getId())
//                .setContentTitle(title)
//                .setContentText(short_text)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(long_text))
//                .setPriority(NotificationManager.IMPORTANCE_HIGH)
//                .setContentIntent(pi)
//                .setAutoCancel(true)
//                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setLargeIcon(Design.RoundRectTransform.change(bd.getBitmap()))
//                .setSmallIcon(R.drawable.mosque3);
//        managerCompat.notify(1234,builder.build());
//    }
//    public static class RoundRectTransform extends BitmapTransformation {
//        ImageView im = null;
//        RoundRectTransform(ImageView im){
//            this.im = im;
//        }
//        public static Bitmap change(Bitmap toTransform){
//            Log.i("my-data","onChange");
//            int w = toTransform.getWidth();
//            int h = toTransform.getHeight();
//            int min = Math.min(w,h);
//            Log.i("my-data",min + "");
//            Bitmap bit = Bitmap.createBitmap(toTransform,0,0,w,h);
//            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setShader(new BitmapShader(bit, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//            Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(result);
//            RectF r = new RectF(0,0,w,h);
//            float round = ((float)min) / 12;
//            c.drawRoundRect(r,round,round,p);
//            return result;
//        }
//        @Override
//        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//            int w = toTransform.getWidth();
//            int h = toTransform.getHeight();
//            Log.i("my-test","w f "+w+" h f "+h);
//            int min = Math.min(w,h);
//            Bitmap bit = Bitmap.createBitmap(toTransform,0,0,w,h);
//            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setShader(new BitmapShader(bit,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//            Bitmap result = pool.get(w,h, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(result);
//            RectF r = new RectF(0,0,w,h);
//            float round = ((float)min) / 12;
//            c.drawRoundRect(r,round,round,p);
////            float nes= (float) w / h ;
////            Log.i("my-test","beforeW "+result.getWidth()+" beforeH "+result.getHeight()+" nes "+nes);
////            int maxW,maxH;
////            if (im.getHeight() != 0){
////                maxH = im.getHeight();
////                maxW = im.getWidth();
////            }else {
////                maxH = im.getMaxHeight();
////                maxW = im.getMaxWidth();
////            }
////            Log.i("my-test","maxW "+maxW+" maxH "+maxH);
////            int widthS = (int) (maxH * nes);
////            if (widthS <= maxW) {
////                Log.i("my-test", "doFW " + widthS + " doFH " + maxH);
////                return Bitmap.createScaledBitmap(result,widthS,maxH,false);
////            }else {
////                Log.i("my-test", "doSW " + maxW + " doSH " + (int) (maxW / nes));
////                return Bitmap.createScaledBitmap(result,maxW, (int) (maxW / nes),false);
////            }
//            return result;
//        }
//        @Override
//        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//            // messageDigest.reset();
//            Log.i("my-data","update");
//        }
//    }
//    public static class CircleTransform extends BitmapTransformation{
//        public static Bitmap change(Bitmap toTransform,ImageView im){
//            int w = toTransform.getWidth();
//            int h = toTransform.getHeight();
//            Log.i("my-test","w f "+w+" h f "+h);
//            int min = Math.min(w,h);
//            int x  = (w - min) / 2;
//            int y = (h - min) / 2;
//            Bitmap bit = Bitmap.createBitmap(toTransform,x,y,min,min);
//            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setShader(new BitmapShader(bit,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//            Bitmap result = Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(result);
//            c.drawCircle((float)min / 2,(float)min / 2,(float)min / 2,p);
//            return result;
//        }
//
//        @Override
//        protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//            int w = toTransform.getWidth();
//            int h = toTransform.getHeight();
//            Log.i("my-test","w f "+w+" h f "+h);
//            int min = Math.min(w,h);
//            int x  = (w - min) / 2;
//            int y = (h - min) / 2;
//            Bitmap bit = Bitmap.createBitmap(toTransform,x,y,min,min);
//            Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
//            p.setShader(new BitmapShader(bit,Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//            Bitmap result = pool.get(min,min, Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(result);
//            c.drawCircle((float)min / 2,(float)min / 2,(float)min / 2,p);
//            return result;
//        }
//        @Override
//        public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//
//        }
//    }
//    public interface PopupAct{
//        void seeMore(Religious religious);
//        void unFollow(Religious religious);
//    }
//    public static PopupMenu createMenu(Context context, View v, final Religious religious, final PopupAct act){
//        final PopupMenu pm = new PopupMenu(context,v);
//        pm.inflate(R.menu.act_more);
//        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.menu_see_more){
//                    act.seeMore(religious);
//                    pm.dismiss();
//                }else if (item.getItemId() == R.id.menu_unf){
//                    act.unFollow(religious);
//                    pm.dismiss();
//                }
//                return false;
//            }
//        });
//        pm.show();
//        return pm;
//    }
//    public interface DoInFailed {
//        public Drawable doFailed();
//        public void videoPrepare(View v);
//        public void videoReady(View v);
//    }


}
