package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ccr.swpiebacklayout.R;
import com.ccr.swpiebacklayout.adapter.RecyclerIndexAdapter;
import com.ccr.swpiebacklayout.util.DataUtil;
import com.ccr.swpiebacklayout.widget.IndexView;

import cn.bingoogolapple.baseadapter.BGADivider;
import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGARVVerticalScrollHelper;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 16:06
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class RecyclerIndexActivity extends BaseActivity implements BGAOnRVItemClickListener {
    private RecyclerIndexAdapter mAdapter;
    private RecyclerView mDataRv;
    private IndexView mIndexView;
    private TextView mTipTv;
    private BGARVVerticalScrollHelper mRecyclerViewScrollHelper;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler_index);
        mDataRv = getViewById(R.id.rv_recyclerindexview_data);

        mIndexView = getViewById(R.id.indexview);
        mTipTv = getViewById(R.id.tv_recyclerindexview_tip);
    }

    @Override
    protected void setListener() {
        mAdapter = new RecyclerIndexAdapter(mDataRv);
        mAdapter.setOnRVItemClickListener(this);

        final BGADivider.StickyDelegate stickyDelegate = new BGADivider.StickyDelegate() {
            @Override
            protected boolean isCategoryFistItem(int position) {
                return mAdapter.isCategory(position);
            }

            @Override
            protected String getCategoryName(int position) {
                return mAdapter.getItem(position).topc;
            }

            @Override
            protected int getFirstVisibleItemPosition() {
                return mRecyclerViewScrollHelper.findFirstVisibleItemPosition();
            }
        };

        mDataRv.addItemDecoration(BGADivider.newBitmapDivider()
                .setStartSkipCount(0)
                .setMarginLeftResource(R.dimen.size_level3)
                .setMarginRightResource(R.dimen.size_level9)
                .setDelegate(stickyDelegate));
        mRecyclerViewScrollHelper = BGARVVerticalScrollHelper.newInstance(mDataRv, new BGARVVerticalScrollHelper.SimpleDelegate() {
            @Override
            public int getCategoryHeight() {
                return stickyDelegate.getCategoryHeight();
            }
        });

        mIndexView.setDelegate(new IndexView.Delegate() {
            @Override
            public void onIndexViewSelectedChanged(IndexView indexView, String text) {
                int position = mAdapter.getPositionForCategory(text.charAt(0));
                if (position != -1) {
                    mRecyclerViewScrollHelper.smoothScrollToPosition(position);
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();

        mIndexView.setTipTv(mTipTv);

        mAdapter.setData(DataUtil.loadIndexModelData());
        mDataRv.setAdapter(mAdapter);
    }

    @Override
    public void onRVItemClick(ViewGroup pViewGroup, View pView, int position) {
        Toast.makeText(this, "选择了城市 " + mAdapter.getItem(position).name, Toast.LENGTH_SHORT).show();
    }
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("测试 RecyclerView");
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
