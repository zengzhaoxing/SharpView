package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.util.AttributeSet;

import com.rey.material.widget.RelativeLayout;

public class SharpRelativeLayout extends RelativeLayout implements SharpView {

    public SharpRelativeLayout(Context context) {
        super(context);
        init(context,null,0);
    }

    public SharpRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SharpRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        mSharpViewRenderProxy = new SharpViewRenderProxy(this, context, attrs, defStyleAttr);
    }

    private SharpViewRenderProxy mSharpViewRenderProxy;

    @Override
    public SharpViewRenderProxy getRenderProxy() {
        return mSharpViewRenderProxy;
    }

}
