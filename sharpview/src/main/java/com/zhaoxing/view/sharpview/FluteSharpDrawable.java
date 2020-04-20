package com.zhaoxing.view.sharpview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;

class FluteSharpDrawable extends GradientDrawable {


    FluteSharpDrawable(Orientation orientation, @ColorInt int[] colors) {
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

    void setArrowDirection(FluteView.ArrowDirection arrowDirection) {
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

    private FluteView.ArrowDirection mArrowDirection;

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
    private Paint mOvalPaint;
    private RectF mRect;
    private RectF mOvalLeft;
    private RectF mOvalTop;
    private RectF mOvalRight;
    private RectF mOvalBottom;

    private Path mPath;
    private Path mOvalPath;

    private PointF[] mPointFsLeft;
    private PointF[] mPointFsTop;
    private PointF[] mPointFsRight;
    private PointF[] mPointFsBottom;

    FluteSharpDrawable() {
        super();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mOvalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOvalPaint.setAntiAlias(true);
        mOvalPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRect = new RectF();
        mPointFsLeft = new PointF[3];
        mPointFsTop = new PointF[3];
        mPointFsRight = new PointF[3];
        mPointFsBottom = new PointF[3];
        mPath = new Path();
        mOvalPath = new Path();
        mOvalLeft = new RectF();
        mOvalTop = new RectF();
        mOvalRight = new RectF();
        mOvalBottom = new RectF();
        mPointFsTop[0] = new PointF();
        mPointFsTop[1] = new PointF();
        mPointFsTop[2] = new PointF();

        mPointFsLeft[0] = new PointF();
        mPointFsLeft[1] = new PointF();
        mPointFsLeft[2] = new PointF();

        mPointFsRight[0] = new PointF();
        mPointFsRight[1] = new PointF();
        mPointFsRight[2] = new PointF();

        mPointFsBottom[0] = new PointF();
        mPointFsBottom[1] = new PointF();
        mPointFsBottom[2] = new PointF();
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
            mRect.set(left, top, right, bottom);
            computeLeft(bounds, left, top, right, bottom);
            computeTop(bounds, left, top, right, bottom);
            computeRight(bounds, left, top, right, bottom);
            computeBottom(bounds, left, top, right, bottom);
            mPath.reset();
            mOvalPath.reset();
            mPath.addRoundRect(mRect, mCornerRadius, mCornerRadius, Path.Direction.CW);

            switch (mArrowDirection) {
                case LEFT:
                    mOvalPath.addArc(mOvalLeft, 270, 180);
                    break;
                case TOP:
                    mOvalPath.addArc(mOvalTop, 0, 180);
                    break;

                case RIGHT:
                    mOvalPath.addArc(mOvalRight, 90, 180);
                    break;
                case BOTTOM:
                    mOvalPath.addArc(mOvalBottom, 180, 180);
                    break;
                case TOP_BOTTOM:
                    mOvalPath.addArc(mOvalTop, 0, 180);
                    mOvalPath.addArc(mOvalBottom, 180, 180);
                    break;
                case LEFT_RIGHT:
                    mOvalPath.addArc(mOvalLeft, 270, 180);
                    mOvalPath.addArc(mOvalRight, 90, 180);
                    break;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mPath.op(mOvalPath, Path.Op.DIFFERENCE);
            }
            mPaint.setColor(mBgColor);
            mOvalPaint.setColor(Color.RED);
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void computeBottom(Rect bounds, int left, int top, int right, int bottom) {
        float length;
        length = Math.max(mRelativePosition * bounds.width() + bounds.top, mSharpSize + mCornerRadius);
        length = Math.min(length, bounds.width() - mSharpSize - mCornerRadius);
        mPointFsBottom[0].set(bounds.left + length, bounds.bottom);
        mPointFsBottom[1].set(mPointFsBottom[0].x - mSharpSize, bottom);
        mPointFsBottom[2].set(mPointFsBottom[0].x + mSharpSize, bottom);
        mOvalBottom.set(mPointFsBottom[1].x,
                mPointFsBottom[1].y - mSharpSize,
                mPointFsBottom[2].x,
                mPointFsBottom[1].y + mSharpSize);
    }

    private void computeRight(Rect bounds, int left, int top, int right, int bottom) {
        float length;
        length = Math.max(mRelativePosition * bounds.height() + bounds.top, mSharpSize + mCornerRadius);
        length = Math.min(length, bounds.height() - mSharpSize - mCornerRadius);
        mPointFsRight[0].set(bounds.right, length + bounds.top);
        mPointFsRight[1].set(right, mPointFsRight[0].y - mSharpSize);
        mPointFsRight[2].set(right, mPointFsRight[0].y + mSharpSize);

        mOvalRight.set(mPointFsRight[1].x - mSharpSize,
                mPointFsRight[1].y,
                mPointFsRight[1].x + mSharpSize,
                mPointFsRight[2].y);
    }

    private void computeTop(Rect bounds, int left, int top, int right, int bottom) {
        float length;
//        top += mSharpSize;
//        mRect.set(left, top, right, bottom);
        length = Math.max(mRelativePosition * bounds.width() + bounds.top, mSharpSize + mCornerRadius);
        length = Math.min(length, bounds.width() - mSharpSize - mCornerRadius);
        mPointFsTop[0].set(bounds.left + length, bounds.top);
        mPointFsTop[1].set(mPointFsTop[0].x - mSharpSize, top);
        mPointFsTop[2].set(mPointFsTop[0].x + mSharpSize, top);
        mOvalTop.set(mPointFsTop[1].x,
                mPointFsTop[1].y - mSharpSize,
                mPointFsTop[2].x,
                mPointFsTop[1].y + mSharpSize);
    }

    private void computeLeft(Rect bounds, int left, int top, int right, int bottom) {
        float length;
        length = Math.max(mRelativePosition * bounds.height() + bounds.top, mSharpSize + mCornerRadius);
        length = Math.min(length, bounds.height() - mSharpSize - mCornerRadius);
        mPointFsLeft[0].set(bounds.left, length + bounds.top);
        mPointFsLeft[1].set(left, mPointFsLeft[0].y - mSharpSize);
        mPointFsLeft[2].set(left, mPointFsLeft[0].y + mSharpSize);
        mOvalLeft.set(
                mPointFsLeft[1].x - mSharpSize,
                mPointFsLeft[1].y,
                mPointFsLeft[1].x + mSharpSize,
                mPointFsLeft[2].y);
    }

}
