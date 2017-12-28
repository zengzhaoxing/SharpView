package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;


public class SharpViewRenderProxy {

    private View mView;

    private float mRadius;

    private int mBackgroundColor;

    private float mRelativePosition;

    private float mSharpSize;

    private float mBorder;

    private int mBorderColor;

    private SharpView.ArrowDirection mArrowDirection = SharpView.ArrowDirection.LEFT;

    public void setBorder(float border) {
        mBorder = border;
        refreshView();
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        refreshView();
    }

    SharpViewRenderProxy(View view, Context context, AttributeSet attrs, int defStyleAttr) {
        mView = view;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SharpTextView, defStyleAttr, 0);
        mRadius = a.getDimension(R.styleable.SharpTextView_radius, 0);
        mBorder = a.getDimension(R.styleable.SharpTextView_border, 0);
        mBackgroundColor = a.getColor(R.styleable.SharpTextView_backgroundColor, 0);
        mBorderColor = a.getColor(R.styleable.SharpTextView_borderColor, 0);
        int direction = a.getInt(R.styleable.SharpTextView_arrowDirection, 3);
        mRelativePosition = a.getFraction(R.styleable.SharpTextView_relativePosition, 1, 1, 0.5f);
        mSharpSize = a.getDimension(R.styleable.SharpLinearLayout_sharpSize,0);
        switch (direction) {
            case 1:
                mArrowDirection = SharpView.ArrowDirection.LEFT;
                mView.setPadding((int) (mView.getPaddingLeft() + mSharpSize),mView.getPaddingTop(),mView.getPaddingRight(),mView.getPaddingBottom());
                break;
            case 2:
                mArrowDirection = SharpView.ArrowDirection.TOP;
                mView.setPadding(mView.getPaddingLeft(), (int) (mView.getPaddingTop() + mSharpSize),mView.getPaddingRight(),mView.getPaddingBottom());
                break;
            case 3:
                mArrowDirection =  SharpView.ArrowDirection.RIGHT;
                mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop(), (int) (mView.getPaddingRight() + mSharpSize),mView.getPaddingBottom());
                break;
            case 4:
                mArrowDirection = SharpView.ArrowDirection.BOTTOM;
                mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop(),mView.getPaddingRight(), (int) (mView.getPaddingBottom() + mSharpSize));
                break;
        }
        a.recycle();
        refreshView();
    }

    private void refreshView() {
        SharpDrawable bd;
        if (mView.getBackground() instanceof SharpDrawable) {
            bd = (SharpDrawable) mView.getBackground();
        } else {
            bd = new SharpDrawable();
        }
        bd.setBgColor(mBackgroundColor);
        bd.setSharpSize(mSharpSize);
        bd.setArrowDirection(mArrowDirection);
        bd.setCornerRadius(mRadius);
        bd.setBorder(mBorder);
        bd.setBorderColor(mBorderColor);
        bd.setRelativePosition(mRelativePosition);
        mView.setBackgroundDrawable(bd);
    }

    public void setRadius(float radius) {
        mRadius = radius;
        refreshView();
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
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

    public void setArrowDirection(SharpView.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
        refreshView();
    }

}
