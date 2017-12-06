package com.zhaoxing.view.sharpview;


public interface SharpView {

    SharpViewRenderProxy getRenderProxy();

    enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }
}
