package com.xunao.onlyone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.dialog.InfoMsgHint;
import com.xunao.onlyone.ui.widget.SwitchButton;
import com.xunao.onlyone.util.CommonUtil;
import com.xunao.onlyone.util.DataClearManager;
import com.xunao.onlyone.util.DataSizeManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/6.
 * cc@cchao.org
 * 设置界面
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.text_cache)
    TextView textCache;
    @BindView(R.id.text_version)
    TextView textVersion;
    @BindView(R.id.rl_open_source)
    RelativeLayout rlOpenSource;

    //通知设置是否打开
    private boolean isCheck = false;

    //清理缓存提示框
    private InfoMsgHint cleanDialog;

    @Override
    protected int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        textCache.setText(DataSizeManager.getTotalCacheSize(this));
        textVersion.setText(CommonUtil.getCurVersion(this));
        switchButton.setCheck(isCheck);
    }

    @Override
    protected void bindEvent() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, OpenSourceActivity.class));
            }
        });
        switchButton.setOnSwitchChangeListener(new SwitchButton.OnSwitchChangeListener() {
            @Override
            public void onChange(boolean isSelect) {
                isCheck = isSelect;
                //TODO 存在本地
            }
        });
    }

    /**
     * 清除缓存
     */
    @OnClick(R.id.rl_clean_cache)
    void clearCache() {
        if (cleanDialog == null) {
            cleanDialog = new InfoMsgHint(this, R.style.MyDialog);
            cleanDialog.setCancleListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cleanDialog.dismiss();
                }
            });
            cleanDialog.setOKListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataClearManager.cleanExternalCache(SettingActivity.this);
                    DataClearManager.cleanInternalCache(SettingActivity.this);
                    textCache.setText(DataSizeManager.getTotalCacheSize(SettingActivity.this));
                    cleanDialog.dismiss();
                }
            });
        }
        cleanDialog.show();
    }

    /**
     * 退出登录
     */
    @OnClick(R.id.btn_logout)
    void logout() {

    }
}
