package com.xunao.onlyone.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/16.
 * cc@cchao.org
 * 客服
 */
public class CustomServiceActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected int bindLayout() {
        return R.layout.activity_custom;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 拨打热线电话
     */
    @OnClick(R.id.rl_hot_wire_tel) void callTel() {}

    @OnClick(R.id.rl_feedback) void openFeedback() {
        startActivity(new Intent(CustomServiceActivity.this, FeedbackActivity.class));
    }
}
