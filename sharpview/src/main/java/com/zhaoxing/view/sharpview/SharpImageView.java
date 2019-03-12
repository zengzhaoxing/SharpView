package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import com.rey.material.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * Created by 曾宪梓 on 2018/6/23.
 */

public class SharpImageView extends ImageView implements SharpView{

    private SoftReference<Bitmap> mSoftBitmap;

    private SoftReference<Bitmap> mSoftOutBitmap;

    private Canvas mCanvas;

    private Canvas mOutCanvas;

    private Paint mPaint = new Paint();

    private Rect rect = new Rect();

    private PorterDuffXfermode mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public SharpImageView(Context context) {
        super(context);
        init(context,null,0);
    }

    public SharpImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SharpImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSoftBitmap.get() == null || mSoftOutBitmap.get() == null) {
            initBitmap();
        }
        mPaint.setAntiAlias(true);
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mOutCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        super.onDraw(mCanvas);
        mPaint.setColor(Color.BLACK);
        mSharpViewRenderProxy.mSharpDrawable.setBounds(0,0,getWidth(),getHeight());
        mSharpViewRenderProxy.mSharpDrawable.setPaint(mPaint);
        mSharpViewRenderProxy.mSharpDrawable.setBgColor(Color.BLACK);
        mSharpViewRenderProxy.mSharpDrawable.draw(mOutCanvas);
        mPaint.setXfermode(mPorterDuffXfermode);
        mOutCanvas.drawBitmap(mSoftBitmap.get(), rect, rect, mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(mSoftOutBitmap.get(), rect, rect, mPaint);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        mSharpViewRenderProxy = new SharpViewRenderProxy(this, context, attrs, defStyleAttr);
    }

    private SharpViewRenderProxy mSharpViewRenderProxy;

    @Override
    public SharpViewRenderProxy getRenderProxy() {
        return mSharpViewRenderProxy;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mSoftBitmap == null || w != oldw || h != oldh) {
            initBitmap();
            rect.set(0,0,getWidth(),getHeight());
        }
    }

    private void initBitmap() {
        Bitmap mBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap mOutBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap.setHasAlpha(true);
        mOutBitmap.setHasAlpha(true);
        mCanvas = new Canvas(mBitmap);
        mOutCanvas = new Canvas(mOutBitmap);
        mSoftBitmap = new SoftReference<>(mBitmap);
        mSoftOutBitmap = new SoftReference<>(mOutBitmap);
    }

}
