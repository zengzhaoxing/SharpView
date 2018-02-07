package com.zhaoxing.view.demo;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhaoxing.view.sharpview.SharpLinearLayout;
import com.zhaoxing.view.sharpview.SharpView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharpLinearLayout sharpLinearLayout = (SharpLinearLayout) findViewById(R.id.sharp_ll);
//        sharpLinearLayout.getRenderProxy().setArrowDirection(SharpView.ArrowDirection.BOTTOM);
//        sharpLinearLayout.getRenderProxy().setSharpSize(50);
//        sharpLinearLayout.getRenderProxy().setRelativePosition(0.8f);
//        sharpLinearLayout.getRenderProxy().setRadius(20);
//        sharpLinearLayout.getRenderProxy().setBackgroundColor(0xff000000);
//        int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };//分别为开始颜色，中间夜色，结束颜色
//
//        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
//        sharpLinearLayout.setBackground(gd);

    }
}
