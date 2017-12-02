package com.ccr.swpiebacklayout.adapter;

import android.support.v7.widget.RecyclerView;

import com.ccr.swpiebacklayout.R;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 08:51
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class ContentAdapter extends BGARecyclerViewAdapter<String> {

    public ContentAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_test);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
        helper.setText(R.id.tv_item_test_title, model);
        helper.setText(R.id.tv_item_test_content, "内容" + (position + 1));
    }
}