package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;


public class FluteViewRenderProxy {

    private View mView;

    public float getRadius() {
        return mRadius;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public float getRelativePosition() {
        return mRelativePosition;
    }

    public float getSharpSize() {
        return mSharpSize;
    }

    public float getBorder() {
        return mBorder;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public int[] getBgColors() {
        return mBgColors;
    }

    public float[] getCornerRadii() {
        return mCornerRadii;
    }

    public FluteView.ArrowDirection getArrowDirection() {
        return mArrowDirection;
    }

    private float mRadius;

    private int mBackgroundColor;

    private float mRelativePosition;

    private float mSharpSize;

    private float mBorder;

    private int mBorderColor;

    public void setBgColor(int[] bgColor) {
        mBgColors = bgColor;
        refreshView();
    }

    private int[] mBgColors;

    public void setCornerRadii(float leftTop, float rightTop, float rightBottom, float leftBottom) {
        mCornerRadii[0] = leftTop;
        mCornerRadii[1] = leftTop;
        mCornerRadii[2] = rightTop;
        mCornerRadii[3] = rightTop;
        mCornerRadii[4] = rightBottom;
        mCornerRadii[5] = rightBottom;
        mCornerRadii[6] = leftBottom;
        mCornerRadii[7] = leftBottom;

    }

    private float[] mCornerRadii = new float[8];

    private FluteView.ArrowDirection mArrowDirection = FluteView.ArrowDirection.LEFT;

    public void setBorder(float border) {
        mBorder = border;
        refreshView();
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        refreshView();
    }

    FluteViewRenderProxy(View view, Context context, AttributeSet attrs, int defStyleAttr) {
        mView = view;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SharpTextView, defStyleAttr, 0);
        mRadius = a.getDimension(R.styleable.SharpTextView_radius, 0);
        mCornerRadii[0] = mCornerRadii[1] = a.getDimension(R.styleable.SharpTextView_left_top_radius, 0);
        mCornerRadii[2] = mCornerRadii[3] = a.getDimension(R.styleable.SharpTextView_right_top_radius, 0);
        mCornerRadii[4] = mCornerRadii[5] = a.getDimension(R.styleable.SharpTextView_right_bottom_radius, 0);
        mCornerRadii[6] = mCornerRadii[7] = a.getDimension(R.styleable.SharpTextView_left_bottom_radius, 0);
        mBorder = a.getDimension(R.styleable.SharpTextView_border, 0);
        mBackgroundColor = a.getColor(R.styleable.SharpTextView_backgroundColor, 0);
        mBorderColor = a.getColor(R.styleable.SharpTextView_borderColor, 0);
        int direction = a.getInt(R.styleable.SharpTextView_arrowDirection, 3);
        mRelativePosition = a.getFraction(R.styleable.SharpTextView_relativePosition, 1, 1, 0.5f);
        mSharpSize = a.getDimension(R.styleable.SharpLinearLayout_sharpSize, 0);
        switch (direction) {
            case 1:
                mArrowDirection = FluteView.ArrowDirection.LEFT;
                break;
            case 2:
                mArrowDirection = FluteView.ArrowDirection.TOP;
                break;
            case 3:
                mArrowDirection = FluteView.ArrowDirection.RIGHT;
                break;
            case 4:
                mArrowDirection = FluteView.ArrowDirection.BOTTOM;
                break;
            case 5:
                mArrowDirection = FluteView.ArrowDirection.TOP_BOTTOM;
                break;
            case 6:
                mArrowDirection = FluteView.ArrowDirection.LEFT_RIGHT;
                break;

        }
        int start = a.getColor(R.styleable.SharpTextView_startBgColor, -1);
        int middle = a.getColor(R.styleable.SharpTextView_middleBgColor, -1);
        int end = a.getColor(R.styleable.SharpTextView_endBgColor, -1);
        if (start != -1 && end != -1) {
            if (middle != -1) {
                mBgColors = new int[]{start, middle, end};
            } else {
                mBgColors = new int[]{start, end};
            }
        }
        a.recycle();
        refreshView();
    }

    FluteSharpDrawable mFluteSharpDrawable;

    private void refreshView() {
        FluteSharpDrawable bd = new FluteSharpDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        mFluteSharpDrawable = bd;
        if (mBgColors != null) {
            bd.setColors(mBgColors);
        } else {
            bd.setBgColor(mBackgroundColor);
        }
        bd.setSharpSize(mSharpSize);
        bd.setArrowDirection(mArrowDirection);
        bd.setCornerRadius(mRadius);
        bd.setBorder(mBorder);
        bd.setBorderColor(mBorderColor);
        bd.setRelativePosition(mRelativePosition);
        if (mRadius == 0) {
            bd.setCornerRadii(mCornerRadii);
        }
        if (mView instanceof SharpImageView) {
            mView.invalidate();
        } else {
            mView.setBackground(bd);
        }
    }

    public void setRadius(float radius) {
        mRadius = radius;
        refreshView();
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        mBgColors = null;
        refreshView();
    }

    public void setRelativePosition(float relativePosition) {
        mRelativePosition = relativePosition;
        refreshView();
    }

    public void setSharpSize(float sharpSize) {
        mSharpSize = sharpSize;
        refreshView();
    }

    public void setArrowDirection(FluteView.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
        refreshView();
    }

}
