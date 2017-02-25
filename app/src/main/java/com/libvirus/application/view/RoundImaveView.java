package com.libvirus.application.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by psu on 2016/6/12.
 */

public class RoundImaveView extends ImageView {


    private Paint mBitmapPaint = new Paint();//画图片
    private Paint mBorderPaint = new Paint();//边框
    private BitmapShader mBitmapShader;
    private Matrix mMatrix = new Matrix();
    private RectF mBitmapRectF = new RectF();//图片的RectF
    private RectF mBorderRectF = new RectF();//边框的Rectf
    private Bitmap mBitmap;//图片的Bitmap
    private float mBorderRadius;//图片的半径
    private int mBorderSize = 5;//px

    public RoundImaveView(Context context) {
        super(context);
        init();
    }

    public RoundImaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmapPaint.setAntiAlias(true);
        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(Color.parseColor("#ff0000"));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mBorderSize > 0) {
            canvas.drawCircle(mBitmapRectF.centerX(), mBitmapRectF.centerY(),
                    mBorderRadius, mBorderPaint);
        }
        canvas.drawCircle(mBitmapRectF.centerX(), mBitmapRectF.centerY(),
                mBorderRadius - mBorderSize / 2, mBitmapPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initCanvas();
    }

    private void initCanvas() {
        getBitMap();
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint.setShader(mBitmapShader);
        calculateBorder();
        calculateMatrix();
        invalidate();
    }

    @Nullable
    private Bitmap getBitMap() {
        if (getDrawable() instanceof BitmapDrawable) {
            mBitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            return mBitmap;
        }
        mBitmap = null;
        return null;
    }

    private void calculateBorder() {
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int sideLength = Math.min(width, height);
        mBorderRadius = sideLength / 2f;
        float top = getPaddingTop() + (height - sideLength) * 0.5f;
        float left = getPaddingLeft() + (width - sideLength) * 0.5f;
        mBorderRectF.set(left, top,
                left + sideLength, top + sideLength);
        mBitmapRectF.set(mBorderRectF);
        mBitmapRectF.inset(mBorderSize, mBorderSize);
    }

    private void calculateMatrix() {
        mMatrix.reset();
        float scale;
        float xt = 0;
        float yt = 0;
        if (mBitmap.getWidth() > mBitmap.getHeight()) {
            scale = mBitmapRectF.height() / (float) mBitmap.getHeight();
            xt = (mBitmapRectF.width() - getPaddingLeft() - mBitmap.getWidth()) * 0.5f;
        } else {
            scale = mBitmapRectF.width() / (float) mBitmap.getWidth();
            yt = (mBitmapRectF.height() - getPaddingTop() - mBitmap.getHeight()) * 0.5f;
        }
        mMatrix.setScale(scale, scale);
//        mMatrix.postTranslate(xt, yt);
        mBitmapShader.setLocalMatrix(mMatrix);
    }

}
