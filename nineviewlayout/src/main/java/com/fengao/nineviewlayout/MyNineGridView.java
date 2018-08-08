package com.fengao.nineviewlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

public class MyNineGridView extends ViewGroup {
    final String TAG = "fengao";
    int cows = 0;
    int columns = 0;

    public MyNineGridView(Context context) {
        this(context, null);
    }

    public MyNineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.parseColor("#FF0000"));
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyNineGridView);
        float dimension = ta.getDimension(R.styleable.MyNineGridView_space_size, 10);
        Log.i(TAG, "MyNineGridView: " + dimension);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        if (childCount <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int width = MeasureSpec.getSize(widthMeasureSpec);
        initMatrix();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int actualWidth = width - paddingLeft - paddingRight;
        int actualHeight = actualWidth / 3 * cows;
        int height = actualHeight + paddingBottom + paddingTop;
        Log.i(TAG, "width: "+width);
        Log.i(TAG, "height: "+height);
        setMeasuredDimension(width, height);
    }

    private void initMatrix() {
        int childCount = getChildCount();
        if (childCount == 3) {
            cows = 2;
            columns = 2;
            return;
        }
        childCount = childCount > 9 ? 9 : childCount;
        double sqrt = Math.sqrt(childCount);
        int ceil = (int) Math.ceil(sqrt);
        cows = ceil;
        if (cows == 1) {
            columns = childCount;
        } else {
            columns = 3;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawRect(getLeft() + 50, getTop() + 50, getRight() - 50, getBottom() - 50, paint);
        Log.i(TAG, "onDraw: ");
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
