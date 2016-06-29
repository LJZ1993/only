package com.xunao.onlyone.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected BaseActivity baseActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(container.getContext()).inflate(bindLayout(), null);
        ButterKnife.bind(this, rootView);

        if (getActivity() instanceof BaseActivity) {
            baseActivity = (BaseActivity) getActivity();
        }

        initData();
        bindEvent();
        return rootView;
    }

    public void openActivity(Class<?> cls) {
        startActivity(new Intent(baseActivity, cls));
    }

    /**
     * 绑定布局文件
     * @return
     */
    protected abstract int bindLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 绑定控件事件
     */
    protected abstract void bindEvent();
}
