package com.xunao.onlyone.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/16.
 * cc@cchao.org
 * 重置密码
 */
public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.edit_pass_again)
    EditText editPassAgain;

    @Override
    protected int bindLayout() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
    }

    @Override
    protected void bindEvent() {
    }

    @OnClick(R.id.btn_commit) void commit() {

    }
}
