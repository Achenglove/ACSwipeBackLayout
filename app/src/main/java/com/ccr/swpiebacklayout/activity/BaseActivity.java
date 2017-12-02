package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ccr.library.ACStatusBarUtil;
import com.ccr.sblibrary.ACSwipeBackHelper;
import com.ccr.swpiebacklayout.R;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-01 15:17
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public abstract class BaseActivity extends AppCompatActivity implements ACSwipeBackHelper.Delegate, View.OnClickListener {
    protected ACSwipeBackHelper mSwipeBackHelper;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        initView(savedInstanceState);
        mToolbar = getViewById(R.id.toolbar);

        setListener();
        processLogic(savedInstanceState);
    }


    /**
     * 初始化滑动关闭界面
     * 在 super.onCreate(savedInstanceState) 之前调用该方法
     *
     * @param v
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new ACSwipeBackHelper(this, this);
        mSwipeBackHelper.setSwipeBackEnable(true);//设置滑动返回是否可用,默认为true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);//设置是否仅仅跟踪左侧边缘滑动返回，默认为true
        mSwipeBackHelper.setIsWeChatStyle(true);// 设置是否是微信滑动返回样式,默认值为true
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);// 设置阴影资源 id,默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setIsNeedShowShadow(true);// 设置是否显示滑动返回的阴影效果,默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);// 设置阴影区域的透明度是否根据滑动的距离渐变,默认值为 true
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);// 设置触发释放后自动滑动返回的阈值,默认值为0.3f
        mSwipeBackHelper.setIsNavigationBarOverlap(false);// 设置底部导航条是否悬浮在内容上,默认值为 false
    }



    /**
     * 是否支持滑动返回。
     * 这里在父类中默认返回 true 来支持滑动返回，如果子类不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回值
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if(mSwipeBackHelper.isSliding()){
            return;
        }
        mSwipeBackHelper.backward();
    }

    /**
     * 设置状态栏颜色
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color) {
        setStatusBarColor(color, ACStatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     * @param color
     * @param statusBarAlpha 透明度
     */
    public void setStatusBarColor(@ColorInt int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        ACStatusBarUtil.setColorForSwipeBack(this, color, statusBarAlpha);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化布局以及View控件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置控件监听事件
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

    }
    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }
}
