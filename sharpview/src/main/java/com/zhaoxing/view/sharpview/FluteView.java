package com.zhaoxing.view.sharpview;

/**
 * @Name: SharpView
 * @Description: 描述信息
 * @Author: Created by heyong on 2020/4/18
 */
public interface FluteView {

    FluteViewRenderProxy getRenderProxy();

    enum ArrowDirection {
        LEFT, TOP, RIGHT, BOTTOM, LEFT_TOP, LEFT_RIGHT, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTTOM, TOP_BOTTOM;
    }
}
