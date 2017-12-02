package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ccr.swpiebacklayout.R;

import cn.bingoogolapple.progressbar.BGAProgressBar;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 16:37
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class WebViewActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private BGARefreshLayout mRefreshLayout;
    private WebView mContentWv;
    private BGAProgressBar mProgressBar;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_webview);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mContentWv = getViewById(R.id.wv_webview_content);
        mProgressBar = getViewById(R.id.progressBar);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);

        mContentWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mRefreshLayout.endRefreshing();
            }
        });
        mContentWv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        findViewById(R.id.transparent).setOnClickListener(this);
        findViewById(R.id.not_transparent).setOnClickListener(this);
        findViewById(R.id.to_transparent).setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();

        initRefreshLayout();

        initWebView();
    }
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("测试下拉刷新和WebView");
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void initRefreshLayout() {
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(this, false);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refresh_moooc);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }

    private void initWebView() {
        mContentWv.getSettings().setJavaScriptEnabled(true);
        mContentWv.loadUrl("https://github.com/Achenglove");
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.transparent) {
            setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        } else if (v.getId() == R.id.not_transparent) {
            setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark), 0);
        } else if (v.getId() == R.id.to_transparent) {
            mSwipeBackHelper.forward(TranslucentActivity.class);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mContentWv.reload();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
