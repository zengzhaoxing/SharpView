package com.example.xianzi.sharpview;


public interface SharpView {

    SharpViewRenderProxy getRenderProxy();

    enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM
    }
}
