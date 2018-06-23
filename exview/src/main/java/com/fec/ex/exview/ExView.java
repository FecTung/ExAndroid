package com.fec.ex.exview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fe2Cu on 06.23.2018
 * github: https://www.github.com/fectung
 * Email : fectung@live.com
 */
public class ExView extends View {

    private int mBackgroundColor;
    private int mLightColor;
    private int mDarkColor;
    private int mMaxRadius;

    private Canvas mCanvas;
    private Paint mLightPaint;
    private Paint mDarkPaint;

    private int mLightRadius;
    private int mDarkRadius;

    public ExView(Context context) {
        this(context, null);
    }

    public ExView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ExView, 0, 0);
        try {
            mBackgroundColor = ta.getColor(R.styleable.ExView_backgroundColor, Color.parseColor("#FFFFFF"));
            mLightColor = ta.getColor(R.styleable.ExView_lightColor, Color.parseColor("#00FF00"));
            mDarkColor = ta.getColor(R.styleable.ExView_darkColor, Color.parseColor("#0000FF"));
            mMaxRadius = ta.getInt(R.styleable.ExView_maxRadius, width / 2);
        } finally {
            ta.recycle();
        }

        setBackgroundColor(mBackgroundColor);

        mLightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLightPaint.setColor(mLightColor);
        mDarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDarkPaint.setColor(mDarkColor);

        mLightRadius = mMaxRadius;
        mDarkRadius = mLightRadius * 2 / 3;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Defines the extra padding for the shape name text

        // Resolve the width based on our minimum and the measure spec
        int minw = mLightRadius*2 + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 0);

        // Ask for a height that would let the view get as big as it can
        int minh = mLightRadius*2 + getPaddingBottom() + getPaddingTop();

        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        // Calling this method determines the measured width and height
        // Retrieve with getMeasuredWidth or getMeasuredHeight methods later
        setMeasuredDimension(w, h);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.mCanvas = canvas;
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, mLightRadius-getPaddingLeft()-getPaddingRight(), mLightPaint);
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, mDarkRadius-getPaddingLeft()-getPaddingRight(), mDarkPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mLightPaint.setColor(mDarkColor);
                mDarkPaint.setColor(mLightColor);
                break;
            case MotionEvent.ACTION_UP:
                mLightPaint.setColor(mLightColor);
                mDarkPaint.setColor(mDarkColor);
        }
        postInvalidate();
        return true;
    }
}
