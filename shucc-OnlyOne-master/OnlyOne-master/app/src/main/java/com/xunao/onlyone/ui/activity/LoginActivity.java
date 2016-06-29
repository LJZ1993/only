package com.xunao.onlyone.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by chenchao on 16/6/13.
 * cc@cchao.org
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_user)
    EditText editUser;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.text_iforgot)
    TextView textIforgot;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.img_qq)
    ImageView imgQQ;
    @BindView(R.id.img_wx)
    ImageView imgWx;
    @BindView(R.id.img_sina)
    ImageView imgSina;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
    }

    @Override
    protected void bindEvent() {
        textIforgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        imgQQ.setOnClickListener(this);
        imgWx.setOnClickListener(this);
        imgSina.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
