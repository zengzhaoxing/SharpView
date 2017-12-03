package com.example.xianzi.sharpview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;


public class SharpViewRenderProxy {

    private View mView;

    public void setRadius(float radius) {
        mRadius = radius;
        refreshView();
    }

    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        refreshView();
    }

    public void setIntDirection(int intDirection) {
        mIntDirection = intDirection;
        refreshView();
    }

    public void setRelativePosition(float relativePosition) {
        mRelativePosition = relativePosition;
        refreshView();
    }

    public void setShareSizePosition(float shareSizePosition) {
        mShareSizePosition = shareSizePosition;
        refreshView();
    }

    private float mRadius;

    private int mBackgroundColor;

    private int mIntDirection;

    private float mRelativePosition;

    private float mShareSizePosition;

    SharpViewRenderProxy(View view, Context context, AttributeSet attrs, int defStyleAttr) {
        mView = view;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SharpView, defStyleAttr, 0);
        mRadius = a.getDimension(R.styleable.SharpView_radius, 0);
        mBackgroundColor = a.getColor(R.styleable.SharpView_backgroundColor, 0);
        mIntDirection = a.getInt(R.styleable.SharpView_arrowDirection, 3);
        mRelativePosition = a.getFraction(R.styleable.SharpView_relativePosition, 1, 1, 0.5f);
        mShareSizePosition = a.getFraction(R.styleable.SharpView_relativePosition, 1, 1, 0.2f);
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
        bd.setSharpSizePosition(mShareSizePosition);
        SharpDrawable.ArrowDirection direction = null;
        switch (mIntDirection) {
            case 1:
                direction = SharpDrawable.ArrowDirection.LEFT;
                //mView.setPadding(mView.getPaddingLeft() + length,mView.getPaddingTop(),mView.getPaddingRight(),mView.getPaddingBottom());
                break;
            case 2:
                direction = SharpDrawable.ArrowDirection.TOP;
                //mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop() + length,mView.getPaddingRight(),mView.getPaddingBottom());
                break;
            case 3:
                direction =  SharpDrawable.ArrowDirection.RIGHT;
                //mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop(),mView.getPaddingRight() + length,mView.getPaddingBottom());
                break;
            case 4:
                direction = SharpDrawable.ArrowDirection.BOTTOM;
                //mView.setPadding(mView.getPaddingLeft(),mView.getPaddingTop(),mView.getPaddingRight(),mView.getPaddingBottom() + length);
                break;
        }
        bd.setArrowDirection(direction);
        bd.setCornerRadius(mRadius);
        bd.setRelativePosition(mRelativePosition);
        mView.setBackgroundDrawable(bd);
    }

}
