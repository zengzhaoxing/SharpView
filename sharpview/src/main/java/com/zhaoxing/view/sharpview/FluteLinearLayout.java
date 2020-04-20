package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.util.AttributeSet;

import com.rey.material.widget.LinearLayout;


public class FluteLinearLayout extends LinearLayout implements FluteView {

    public FluteLinearLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public FluteLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public FluteLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mFluteViewRenderProxy = new FluteViewRenderProxy(this, context, attrs, defStyleAttr);
    }

    private FluteViewRenderProxy mFluteViewRenderProxy;

    @Override
    public FluteViewRenderProxy getRenderProxy() {
        return mFluteViewRenderProxy;
    }

}
