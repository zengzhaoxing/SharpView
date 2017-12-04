package com.example.xianzi.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xianzi.sharpview.SharpLinearLayout;
import com.example.xianzi.sharpview.SharpView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharpLinearLayout sharpLinearLayout = (SharpLinearLayout) findViewById(R.id.sharp_ll);
        sharpLinearLayout.getRenderProxy().setArrowDirection(SharpView.ArrowDirection.BOTTOM);
        sharpLinearLayout.getRenderProxy().setSharpSize(50);
        sharpLinearLayout.getRenderProxy().setRelativePosition(0.8f);
        sharpLinearLayout.getRenderProxy().setRadius(20);
        sharpLinearLayout.getRenderProxy().setBackgroundColor(0xff000000);
    }
}
