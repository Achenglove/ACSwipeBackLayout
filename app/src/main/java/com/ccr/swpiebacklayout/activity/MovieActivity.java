package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ccr.swpiebacklayout.R;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 15:55
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class MovieActivity extends BaseActivity {
    private Button mControlBtn;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie);
        mControlBtn = getViewById(R.id.btn_movie_control);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mSwipeBackHelper.setIsNavigationBarOverlap(true);
    }
    public void hideNavigationBar(View view) {
        mControlBtn.postDelayed(new Runnable() {
            @Override
            public void run() {
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);

                mControlBtn.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }
}
