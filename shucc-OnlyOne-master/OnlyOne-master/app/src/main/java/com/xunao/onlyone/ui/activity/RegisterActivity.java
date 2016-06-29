package com.xunao.onlyone.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/16.
 * cc@cchao.org
 * 注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_login)
    TextView textLogin;
    @BindView(R.id.edit_tel)
    EditText editTel;
    @BindView(R.id.edit_sms)
    EditText editSms;
    @BindView(R.id.text_send)
    TextView textSend;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.img_code)
    ImageView imgCode;

    @Override
    protected int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 打开用户协议
     */
    @OnClick(R.id.text_user_agreement) void openUserAgreement() {}

    @OnClick(R.id.btn_commit) void commit() {}
}
