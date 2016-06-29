package com.xunao.onlyone.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 */
public abstract class BaseActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ButterKnife.bind(this);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initData();
        bindEvent();
    }

    /**
     * 隐藏软键盘
     * @param view
     */
    public void hideKeyboard(View view) {
        if(inputMethodManager.isActive()){
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    public void setImgBack(ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 跳转
     *
     * @param cls
     * @param requestCode
     */
    public void openActivity(Class<?> cls, int requestCode) {
        startActivityForResult(new Intent(this, cls), requestCode);
    }

    public void openActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
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
