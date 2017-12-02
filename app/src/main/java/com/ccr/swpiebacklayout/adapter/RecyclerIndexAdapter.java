package com.ccr.swpiebacklayout.adapter;

import android.support.v7.widget.RecyclerView;

import com.ccr.swpiebacklayout.R;
import com.ccr.swpiebacklayout.model.IndexModel;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 16:31
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */
public class RecyclerIndexAdapter extends BGARecyclerViewAdapter<IndexModel> {

    public RecyclerIndexAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_index_city);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, IndexModel model) {
        helper.setText(R.id.tv_item_index_city, model.name);
    }

    public boolean isCategory(int position) {
        int category = getItem(position).topc.charAt(0);
        return position == getPositionForCategory(category);
    }

    public int getPositionForCategory(int category) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = getItem(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == category) {
                return i;
            }
        }
        return -1;
    }
}