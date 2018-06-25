package com.zhaoxing.view.demo;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhaoxing.view.sharpview.SharpLinearLayout;
import com.zhaoxing.view.sharpview.SharpView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Glide.with(this).load("http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&cs=415293130,2419074865&os=1556766946,250663840&simid=4145280632,499508967&pn=1&rn=1&di=155407800570&ln=1986&fr=&fmq=1529895389158_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0117e2571b8b246ac72538120dd8a4.jpg%401280w_1l_2o_100sh.jpg&rpstart=0&rpnum=0&adpicid=0").placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(((ImageView) findViewById(R.id.eee)));
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
