package com.example.xianzi.sharpview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

class SharpDrawable extends Drawable {

    enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }

    void setBgColor(int bgColor) {
        mBgColor = bgColor;
    }

    void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
    }

    void setArrowDirection(ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
    }

    void setRelativePosition(float relativePosition) {
        mRelativePosition = Math.min(Math.max(relativePosition,0),1);
    }

    void setSharpSizePosition(float sharpSizePosition) {
        mSharpSizePosition = sharpSizePosition;
    }

    private float mSharpSizePosition;

    private int mBgColor;

    private float mCornerRadius;

    private ArrowDirection mArrowDirection;

    /**
     * from 0 to 1
     */
    private float mRelativePosition;

    private Paint mPaint;

    private RectF mRect;

    private Path mPath;

    private PointF[] mPointFs;

    SharpDrawable() {
        mPaint = new Paint();
        mRect = new RectF();
        mPointFs = new PointF[3];
        mPath = new Path();
        mPointFs[0] = new PointF();
        mPointFs[1] = new PointF();
        mPointFs[2] = new PointF();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPaint.setColor(mBgColor);
        Rect bounds = getBounds();
        int left = bounds.left;
        int top = bounds.top;
        int right = bounds.right;
        int bottom = bounds.bottom;
        int sharpLength;
        float length;
        switch (mArrowDirection){
            case LEFT:
                sharpLength = (int) (bounds.height() * mSharpSizePosition);
                left += sharpLength;
                length = Math.max(mRelativePosition * bounds.height() + bounds.top, sharpLength + mCornerRadius);
                length = Math.min(length,bounds.height() - sharpLength - mCornerRadius);
                mPointFs[0].set(bounds.left,length + bounds.top);
                mPointFs[1].set(mPointFs[0].x + sharpLength,mPointFs[0].y - sharpLength);
                mPointFs[2].set(mPointFs[0].x + sharpLength,mPointFs[0].y + sharpLength);
                break;
            case TOP:
                sharpLength = (int) (bounds.width() * mSharpSizePosition);
                top += sharpLength;
                length = Math.max(mRelativePosition * bounds.width() + bounds.top, sharpLength + mCornerRadius);
                length = Math.min(length,bounds.width() - sharpLength - mCornerRadius);
                mPointFs[0].set(bounds.left + length,bounds.top);
                mPointFs[1].set(mPointFs[0].x - sharpLength, mPointFs[0].y + sharpLength);
                mPointFs[2].set(mPointFs[0].x + sharpLength, mPointFs[0].y + sharpLength);
                break;
            case RIGHT:
                sharpLength = (int) (bounds.height() * mSharpSizePosition);
                right -= sharpLength;
                length = Math.max(mRelativePosition * bounds.height() + bounds.top, sharpLength + mCornerRadius);
                length = Math.min(length,bounds.height() - sharpLength - mCornerRadius);
                mPointFs[0].set(bounds.right,length + bounds.top);
                mPointFs[1].set(mPointFs[0].x - sharpLength,mPointFs[0].y - sharpLength);
                mPointFs[2].set(mPointFs[0].x - sharpLength,mPointFs[0].y + sharpLength);
                break;
            case BOTTOM:
                sharpLength = (int) (bounds.height() * mSharpSizePosition);
                bottom -= sharpLength;
                length = Math.max(mRelativePosition * bounds.width() + bounds.top, sharpLength + mCornerRadius);
                length = Math.min(length,bounds.width() - sharpLength - mCornerRadius);
                mPointFs[0].set(bounds.left + length,bounds.bottom);
                mPointFs[1].set(mPointFs[0].x - sharpLength, mPointFs[0].y - sharpLength);
                mPointFs[2].set(mPointFs[0].x + sharpLength, mPointFs[0].y - sharpLength);
                break;
        }
        mRect.set(left,top,right,bottom);

        mPath.reset();
        mPath.moveTo(mPointFs[0].x, mPointFs[0].y);
        mPath.lineTo(mPointFs[1].x, mPointFs[1].y);
        mPath.lineTo(mPointFs[2].x, mPointFs[2].y);
        mPath.close();
        mPaint.setAntiAlias(true);
        canvas.drawRoundRect(mRect,mCornerRadius,mCornerRadius,mPaint);
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
