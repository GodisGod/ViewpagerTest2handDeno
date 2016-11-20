package com.feiyu.viewpagertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.feiyu.viewpagertest.adapter.CardPagerAdapter;
import com.feiyu.viewpagertest.entity.Item;
import com.feiyu.viewpagertest.transformer.DepthPageTransformer;
import com.feiyu.viewpagertest.transformer.ShadowTransformer;
import com.feiyu.viewpagertest.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RobotViewpager viewPager;
    private List<Item> mlist = new ArrayList<>();
    private int[] mImgs = {R.mipmap.meinv1, R.mipmap.meinv2, R.mipmap.meinv3, R.mipmap.meinv4, R.mipmap.meinv5};
    private CardPagerAdapter mCardAdapter;
    private DepthPageTransformer depthPageTransformer;
    private ZoomOutPageTransformer zoomOutPageTransformer;
    private ShadowTransformer shadowTransformer;

    private Button btn_sure;
    private Button btn_begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_sure = (Button) findViewById(R.id.btn_sure);
        viewPager = (RobotViewpager) findViewById(R.id.viewpager);
        mCardAdapter = new CardPagerAdapter(this, mlist);
        depthPageTransformer = new DepthPageTransformer();
        zoomOutPageTransformer = new ZoomOutPageTransformer();
        shadowTransformer = new ShadowTransformer(viewPager, mCardAdapter);
        shadowTransformer.setCanAlpha(true);
        shadowTransformer.setAlpha(0.5f, true);
        shadowTransformer.setCanScale(true);
        shadowTransformer.setScale(0.2f,true);

        viewPager.setAdapter(mCardAdapter);
//        viewPager.setPageTransformer(false, mCardShadowTransformer);
        viewPager.setPageTransformer(true, shadowTransformer);
        viewPager.setOffscreenPageLimit(4);
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置不可滑动
                viewPager.setNoScroll(true);
                //设置全屏
                shadowTransformer.setScale(1f, true);
                shadowTransformer.setAlpha(0.0f, true);
            }
        });
        btn_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setNoScroll(false);
                shadowTransformer.setScale(0.2f, true);
                shadowTransformer.setAlpha(0.5f, true);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < mImgs.length; i++) {
            Item item = new Item();
            item.setImg(mImgs[i]);
            item.setName(i + "km");
            mlist.add(item);
        }
    }
}
