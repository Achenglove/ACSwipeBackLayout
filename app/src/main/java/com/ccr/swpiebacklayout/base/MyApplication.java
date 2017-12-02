package com.ccr.swpiebacklayout.base;

import android.app.Application;

import com.ccr.sblibrary.ACSwipeBackHelper;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-01 15:11
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        ACSwipeBackHelper.init(this, null);
    }
}
