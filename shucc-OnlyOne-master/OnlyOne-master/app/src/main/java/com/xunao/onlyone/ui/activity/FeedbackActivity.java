package com.xunao.onlyone.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/14.
 * cc@cchao.org
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_tel)
    EditText editTel;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    @Override
    protected int bindLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
        editTitle.requestFocus();
    }

    @Override
    protected void bindEvent() {
    }

    @OnClick(R.id.btn_commit) void commit() {
    }
}
