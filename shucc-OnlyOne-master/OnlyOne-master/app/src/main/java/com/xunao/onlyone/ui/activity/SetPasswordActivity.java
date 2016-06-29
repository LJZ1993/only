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
 * 设置密码
 */
public class SetPasswordActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_pass)
    EditText editPass;
    @BindView(R.id.edit_pass_again)
    EditText editPassAgain;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.img_code)
    ImageView imgCode;

    @Override
    protected int bindLayout() {
        return R.layout.activity_set_password;
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
