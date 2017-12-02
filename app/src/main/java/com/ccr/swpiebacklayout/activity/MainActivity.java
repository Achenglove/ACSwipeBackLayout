package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.view.View;

import com.ccr.swpiebacklayout.R;

public class MainActivity extends BaseActivity {

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mToolbar=getViewById(R.id.toolbar);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button1:
                mSwipeBackHelper.forward(TestActivity.class);
                break;
            case R.id.button2:
                mSwipeBackHelper.forward(SwipeDeleteActivity.class);
                break;
            case R.id.button3:
                mSwipeBackHelper.forward(TranslucentActivity.class);
                break;
            case R.id.button4:
                mSwipeBackHelper.forward(WebViewActivity.class);
                break;
            case R.id.button5:
                mSwipeBackHelper.forward(RecyclerIndexActivity.class);
                break;
            case R.id.button6:
                mSwipeBackHelper.forward(EditTextActivity.class);
                break;
            case R.id.button7:
                mSwipeBackHelper.forward(MovieActivity.class);
                break;
        }
    }
}
