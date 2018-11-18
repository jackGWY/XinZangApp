package com.guo.xinzangapp;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class homepageActivity extends AppCompatActivity implements
        android.view.View.OnClickListener{
    private ViewPager mViewPager;// 用来放置界面切换
    private PagerAdapter mPagerAdapter;// 初始化View适配器
    private List<View> mViews = new ArrayList<View>();// 用来存放Tab01-04
    // 四个Tab，每个Tab包含一个按钮
    private LinearLayout mTabWeiXin;
    private LinearLayout mTabAddress;
    private LinearLayout mTabFrd;
    private LinearLayout mTabSetting;
    // 四个按钮
    private ImageButton mWeiXinImg;
    private ImageButton mAddressImg;
    private ImageButton mFrdImg;
    private ImageButton mSettingImg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initView();
        initViewPage();
        initEvent();
    }
    private void initEvent() {
        mTabWeiXin.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            /**
             *ViewPage左右滑动时
             */
            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetImg();
                        mWeiXinImg.setImageResource(R.drawable.android);
                        break;
                    case 1:
                        resetImg();
                        mAddressImg.setImageResource(R.drawable.shezhi);
                        break;
                    case 2:
                        resetImg();
                        mFrdImg.setImageResource(R.drawable.talk);
                        break;
                    case 3:
                        resetImg();
                        mSettingImg.setImageResource(R.drawable.me);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * 初始化设置
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpage);
        // 初始化四个LinearLayout
        mTabWeiXin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);
        // 初始化四个按钮
        mWeiXinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mAddressImg = (ImageButton) findViewById(R.id.id_tab_address_img);
        mFrdImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
    }

    /**
     * 初始化ViewPage
     */
    private void initViewPage() {

        // 初妈化四个布局
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View tab01 = mLayoutInflater.inflate(R.layout.tab01, null);
        View tab02 = mLayoutInflater.inflate(R.layout.tab02, null);
        View tab03 = mLayoutInflater.inflate(R.layout.tab03, null);
        View tab04 = mLayoutInflater.inflate(R.layout.tab04, null);

        mViews.add(tab01);
        mViews.add(tab02);
        mViews.add(tab03);
        mViews.add(tab04);

        // 适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViews.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return mViews.size();
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }

    /**
     * 判断哪个要显示，及设置按钮图片
     */
    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.id_tab_weixin:
                mViewPager.setCurrentItem(0);
                resetImg();
                mWeiXinImg.setImageResource(R.drawable.android);
                break;
            case R.id.id_tab_address:
                mViewPager.setCurrentItem(1);
                resetImg();
                mAddressImg.setImageResource(R.drawable.shezhi);
                break;
            case R.id.id_tab_frd:
                mViewPager.setCurrentItem(2);
                resetImg();
                mFrdImg.setImageResource(R.drawable.talk);
                break;
            case R.id.id_tab_settings:
                mViewPager.setCurrentItem(3);
                resetImg();
                mSettingImg.setImageResource(R.drawable.me);
                break;
            default:
                break;
        }
    }

    /**
     * 把所有图片变暗
     */
    private void resetImg() {
        mWeiXinImg.setImageResource(R.drawable.android);
        mAddressImg.setImageResource(R.drawable.shezhi);
        mFrdImg.setImageResource(R.drawable.talk);
        mSettingImg.setImageResource(R.drawable.me);
    }

}
