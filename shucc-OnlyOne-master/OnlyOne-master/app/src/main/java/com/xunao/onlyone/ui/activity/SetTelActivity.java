package com.xunao.onlyone.ui.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/16.
 * cc@cchao.org
 * 设定手机号码
 */
public class SetTelActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
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
        return R.layout.activity_set_tel;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
    }

    @Override
    protected void bindEvent() {
        if (TextUtils.isEmpty(editTel.getText().toString())) {
        } else {
            //TODO 发送短信验证码
        }
    }

    @OnClick(R.id.text_send) void send() {

    }

    @OnClick(R.id.btn_save) void save() {
        if (!TextUtils.isEmpty(editTel.getText().toString())) {
            App.getSpUtil().saveTel(editTel.getText().toString());
        }
        ToastUtil.Infotoast(this, getResources().getString(R.string.save_success));
    }
}
