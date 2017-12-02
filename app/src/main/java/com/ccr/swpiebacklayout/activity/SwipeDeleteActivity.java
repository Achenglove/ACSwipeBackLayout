package com.ccr.swpiebacklayout.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ccr.swpiebacklayout.R;
import com.ccr.swpiebacklayout.adapter.SwipeDeleteAdapter;
import com.ccr.swpiebacklayout.util.DataUtil;

import cn.bingoogolapple.baseadapter.BGADivider;
import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.baseadapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGAOnRVItemLongClickListener;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 16:23
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class SwipeDeleteActivity extends BaseActivity implements BGAOnRVItemClickListener, BGAOnRVItemLongClickListener, BGAOnItemChildClickListener, BGAOnItemChildLongClickListener {
    private SwipeDeleteAdapter mAdapter;
    private RecyclerView mDataRv;
    @Override
    public void onItemChildClick(ViewGroup pViewGroup, View childView, int position) {
        if (childView.getId() == R.id.tv_item_bgaswipe_delete) {
            mAdapter.closeOpenedSwipeItemLayoutWithAnim();
            mAdapter.removeItem(position);
        }
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup pViewGroup, View childView, int position) {
        if (childView.getId() == R.id.tv_item_bgaswipe_delete) {
            Toast.makeText(this, "长按了删除 " + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onRVItemClick(ViewGroup pViewGroup, View pView, int position) {
        Toast.makeText(this, "点击了条目 " + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onRVItemLongClick(ViewGroup pViewGroup, View pView, int position) {
        Toast.makeText(this, "长按了条目 " + mAdapter.getItem(position).mTitle, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_swipe_delete);
        mDataRv = getViewById(R.id.rv_swipe_delete_data);
    }

    @Override
    protected void setListener() {
        mAdapter = new SwipeDeleteAdapter(mDataRv);
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);

        mDataRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    mAdapter.closeOpenedSwipeItemLayoutWithAnim();
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        initToolbar();

        mAdapter.setData(DataUtil.loadNormalModelDatas());
        mDataRv.addItemDecoration(BGADivider.newBitmapDivider());
        mDataRv.setAdapter(mAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("测试滑动删除");
        setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
