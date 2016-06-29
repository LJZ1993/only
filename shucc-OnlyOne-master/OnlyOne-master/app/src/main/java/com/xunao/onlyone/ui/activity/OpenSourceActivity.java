package com.xunao.onlyone.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.xunao.onlyone.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/6/15.
 * cc@cchao.org
 * 开放源代码协议
 */
public class OpenSourceActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source);
        ButterKnife.bind(this);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        webView.loadUrl("file:///android_asset/opensource.html");
    }
}
