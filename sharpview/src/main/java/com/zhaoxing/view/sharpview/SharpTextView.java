package com.zhaoxing.view.sharpview;

import android.content.Context;
import android.util.AttributeSet;

import com.rey.material.widget.TextView;

public class SharpTextView extends TextView implements SharpView {

    public SharpTextView(Context context) {
        super(context);
        init(context,null,0);
    }

    public SharpTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public SharpTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
