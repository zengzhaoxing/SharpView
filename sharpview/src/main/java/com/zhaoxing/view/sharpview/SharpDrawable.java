package com.zhaoxing.view.sharpview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.Log;

class SharpDrawable extends GradientDrawable {


    SharpDrawable(Orientation orientation, @ColorInt int[] colors) {
        super(orientation, colors);
        init();
    }

    void setBgColor(int bgColor) {
        mBgColor = bgColor;
        super.setColor(bgColor);
    }

    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
        super.setCornerRadius(cornerRadius);
    }

    void setArrowDirection(SharpView.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
    }

    void setRelativePosition(float relativePosition) {
        mRelativePosition = Math.min(Math.max(relativePosition, 0), 1);
    }

    void setBorder(float border) {
        mBorder = border;
        super.setStroke((int) mBorder, mBorderColor);
    }

    void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        super.setStroke((int) mBorder, mBorderColor);
    }

    void setSharpSize(float sharpSize) {
        mSharpSize = sharpSize;
    }

    private float mSharpSize;

    private int mBgColor;

    private float mCornerRadius;

    private SharpView.ArrowDirection mArrowDirection;

    private float mBorder;

    private int mBorderColor;

    /**
     * from 0 to 1
     */
    private float mRelativePosition;

    void setPaint(Paint paint) {
        mPaint = paint;
    }

    private Paint mPaint;

    private RectF mRect;

    private Path mPath;

    private PointF[] mPointFs;

    private RectF mOvalRect[] = new RectF[4];

    SharpDrawable() {
        super();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mRect = new RectF();
        mPointFs = new PointF[3];
        mPath = new Path();
        mPointFs[0] = new PointF();
        mPointFs[1] = new PointF();
        mPointFs[2] = new PointF();

        mOvalRect[0] = new RectF();
        mOvalRect[1] = new RectF();
        mOvalRect[2] = new RectF();
        mOvalRect[3] = new RectF();
    }


    @Override
    public void draw(Canvas canvas) {
        if (mSharpSize == 0) {
            super.draw(canvas);
        } else {
            Rect bounds = getBounds();
            int left = bounds.left;
            int top = bounds.top;
            int right = bounds.right;
            int bottom = bounds.bottom;
            float length;
            switch (mArrowDirection) {
                case LEFT:
                    left += mSharpSize;
                    length = Math.max(mRelativePosition * bounds.height() + bounds.top, mSharpSize + mCornerRadius);
                    length = Math.min(length, bounds.height() - mSharpSize - mCornerRadius);
                    mPointFs[0].set(bounds.left, length + bounds.top);
                    mPointFs[1].set(left, mPointFs[0].y - mSharpSize);
                    mPointFs[2].set(left, mPointFs[0].y + mSharpSize);
                    mRect.set(left, top, right, bottom);
                    break;
                case TOP:
                    top += mSharpSize;
                    length = Math.max(mRelativePosition * bounds.width() + bounds.top, mSharpSize + mCornerRadius);
                    length = Math.min(length, bounds.width() - mSharpSize - mCornerRadius);
                    mPointFs[0].set(bounds.left + length, bounds.top);
                    mPointFs[1].set(mPointFs[0].x - mSharpSize, top);
                    mPointFs[2].set(mPointFs[0].x + mSharpSize, top);
                    mRect.set(left, top, right, bottom);
                    break;
                case RIGHT:
                    right -= mSharpSize;
                    length = Math.max(mRelativePosition * bounds.height() + bounds.top, mSharpSize + mCornerRadius);
                    length = Math.min(length, bounds.height() - mSharpSize - mCornerRadius);
                    mPointFs[0].set(bounds.right, length + bounds.top);
                    mPointFs[1].set(right, mPointFs[0].y - mSharpSize);
                    mPointFs[2].set(right, mPointFs[0].y + mSharpSize);
                    mRect.set(left, top, right, bottom);
                    break;
                case BOTTOM:
                    bottom -= mSharpSize;
                    length = Math.max(mRelativePosition * bounds.width() + bounds.top, mSharpSize + mCornerRadius);
                    length = Math.min(length, bounds.width() - mSharpSize - mCornerRadius);
                    mPointFs[0].set(bounds.left + length, bounds.bottom);
                    mPointFs[1].set(mPointFs[0].x - mSharpSize, bottom);
                    mPointFs[2].set(mPointFs[0].x + mSharpSize, bottom);
                    mRect.set(left, top, right, bottom);
                    break;
            }
            mPath.reset();
            mPath.addRoundRect(mRect, mCornerRadius, mCornerRadius, Path.Direction.CW);
            mPath.moveTo(mPointFs[0].x, mPointFs[0].y);
            mPath.lineTo(mPointFs[1].x, mPointFs[1].y);
            mPath.lineTo(mPointFs[2].x, mPointFs[2].y);
            mPath.lineTo(mPointFs[0].x, mPointFs[0].y);
            mPaint.setColor(mBgColor);
            canvas.drawPath(mPath, mPaint);



            mPaint.setColor(mBorderColor);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mBorder);
            switch (mArrowDirection) {
                case LEFT:
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.top,mRect.right - mCornerRadius,mRect.top,mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.bottom,mRect.right - mCornerRadius,mRect.bottom,mPaint);
                    canvas.drawLine(mRect.right,mRect.top + mCornerRadius,mRect.right,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.left,mRect.top + mCornerRadius,mRect.left,mPointFs[1].y,mPaint);
                    canvas.drawLine(mRect.left,mPointFs[2].y,mRect.left,mRect.bottom - mCornerRadius,mPaint);
                    break;
                case TOP:
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.bottom,mRect.right - mCornerRadius,mRect.bottom,mPaint);
                    canvas.drawLine(mRect.left,mRect.top + mCornerRadius,mRect.left,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.right,mRect.top + mCornerRadius,mRect.right,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.top,mPointFs[1].x,mRect.top,mPaint);
                    canvas.drawLine(mPointFs[2].x, mRect.top, mRect.right - mCornerRadius, mRect.top, mPaint);
                    break;
                case RIGHT:
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.top,mRect.right - mCornerRadius,mRect.top,mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.bottom,mRect.right - mCornerRadius,mRect.bottom,mPaint);
                    canvas.drawLine(mRect.left,mRect.top + mCornerRadius,mRect.left,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.right,mRect.top + mCornerRadius,mRect.right,mPointFs[1].y,mPaint);
                    canvas.drawLine(mRect.right,mPointFs[2].y,mRect.right,mRect.bottom - mCornerRadius,mPaint);
                    break;
                case BOTTOM:
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.top,mRect.right - mCornerRadius,mRect.top,mPaint);
                    canvas.drawLine(mRect.left,mRect.top + mCornerRadius,mRect.left,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.right,mRect.top + mCornerRadius,mRect.right,mRect.bottom - mCornerRadius,mPaint);
                    canvas.drawLine(mRect.left + mCornerRadius,mRect.bottom,mPointFs[1].x,mRect.bottom,mPaint);
                    canvas.drawLine(mPointFs[2].x, mRect.bottom, mRect.right - mCornerRadius, mRect.bottom, mPaint);
                    break;
            }

            if (mCornerRadius > 0) {
                float d = 2 * mCornerRadius;

                mOvalRect[0].set(mRect.left,mRect.top,mRect.left + d,mRect.top + d);
                mOvalRect[1].set(mRect.right - d,mRect.top,mRect.right,mRect.top + d);
                mOvalRect[2].set(mRect.left,mRect.bottom - d,mRect.left + d,mRect.bottom);
                mOvalRect[3].set(mRect.right - d,mRect.bottom - d,mRect.right,mRect.bottom);
                canvas.drawArc(mOvalRect[0],180,90,false,mPaint);
                canvas.drawArc(mOvalRect[1],-90,90,false,mPaint);
                canvas.drawArc(mOvalRect[2],90,90,false,mPaint);
                canvas.drawArc(mOvalRect[3],90,-90,false,mPaint);
            }

            canvas.drawLine(mPointFs[0].x, mPointFs[0].y, mPointFs[1].x, mPointFs[1].y, mPaint);
            canvas.drawLine(mPointFs[0].x, mPointFs[0].y, mPointFs[2].x, mPointFs[2].y, mPaint);

        }
    }

}
