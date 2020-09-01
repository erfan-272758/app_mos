package com.google.myapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class Scroll extends ScrollView {
    private boolean enable = true;
    public Scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!enable)
            return false;
        return super.onInterceptTouchEvent(ev);
    }
}
