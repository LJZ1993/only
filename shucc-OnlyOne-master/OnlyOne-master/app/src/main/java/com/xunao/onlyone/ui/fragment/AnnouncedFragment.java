package com.xunao.onlyone.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseFragment;
import com.xunao.onlyone.ui.widget.MyPtrClassicFrameLayout;

import butterknife.BindView;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 揭晓
 */
public class AnnouncedFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout ptrLayout;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_announced;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
