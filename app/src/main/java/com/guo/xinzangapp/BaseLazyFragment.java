package com.guo.xinzangapp;

import android.support.v4.app.Fragment;

/**
 * Created by guo_w on 2019/1/8.
 */

public abstract class BaseLazyFragment extends Fragment {

    /**
     * 判断当前的Fragment是否可见(相对于其他的Fragment)
     */
    protected boolean mIsVisible;

    /**
     * 标志位，标志已经初始化完成
     */
    protected boolean mIsprepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean mHasLoadedOnce;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //设置Fragment的可见状态
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//getUserVisibleHint获取Fragment可见状态
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }

        if (isResumed()) {
            onVisibilityChangedToUser(isVisibleToUser);
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        stopLoad();
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }

    //region 神策埋点需要，统计Fragement 可见时间
    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisibilityChangedToUser(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onVisibilityChangedToUser(false);
        }
    }

    /**
     * 当Fragment对用户的可见性发生了改变的时候就会回调此方法
     *
     * @param isVisibleToUser true：用户能看见当前Fragment；false：用户看不见当前Fragment
     */
    public void onVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            visibleToUser();
        } else {
            inVisibleToUser();
        }
    }

    protected void visibleToUser() {

    }

    protected void inVisibleToUser() {

    }
}

