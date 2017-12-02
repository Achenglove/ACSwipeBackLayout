package com.ccr.swpiebacklayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ccr.swpiebacklayout.fragment.ContentFragment;

/**
 * @Author: Acheng
 * @Email: 345887272@qq.com
 * @Date: 2017-12-02 09:04
 * @Version: V1.0 <描述当前版本功能>
 * 在此写用途
 */

public class ContentPagerAdapter extends FragmentPagerAdapter {
    private ContentFragment mOneFragment;
    private ContentFragment mTwoFragment;
    private ContentFragment mThreeFragment;
    public ContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            if (mOneFragment == null) {
                mOneFragment = ContentFragment.newInstance(position);
            }
            return mOneFragment;
        } else if (position == 1) {
            if (mTwoFragment == null) {
                mTwoFragment = ContentFragment.newInstance(position);
            }
            return mTwoFragment;
        } else {
            if (mThreeFragment == null) {
                mThreeFragment = ContentFragment.newInstance(position);
            }
            return mThreeFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return "第" + (position + 1) + "页";
    }
}
