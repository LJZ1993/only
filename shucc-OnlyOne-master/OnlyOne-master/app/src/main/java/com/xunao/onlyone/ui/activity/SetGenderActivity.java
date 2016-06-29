package com.xunao.onlyone.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.util.ToastUtil;

import butterknife.BindView;

/**
 * Created by chenchao on 16/6/8.
 * cc@cchao.org
 * 设定性别
 */
public class SetGenderActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_ok)
    TextView textOk;
    @BindView(R.id.img_man)
    ImageView imgMan;
    @BindView(R.id.img_woman)
    ImageView imgWoman;

    private boolean isMan = true;

    @Override
    protected int bindLayout() {
        return R.layout.activity_set_gender;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
        isMan = App.getSpUtil().getGender().equals("男") ? true : false;
        changeRadio();
    }

    @Override
    protected void bindEvent() {
        textOk.setOnClickListener(this);
        imgMan.setOnClickListener(this);
        imgWoman.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_ok:
                App.getSpUtil().saveGender(isMan);
                ToastUtil.Infotoast(this, getResources().getString(R.string.save_success));
                finish();
                break;
            case R.id.img_man:
                if (!isMan) {
                    isMan = true;
                    changeRadio();
                }
                break;
            case R.id.img_woman:
                if (isMan) {
                    isMan = false;
                    changeRadio();
                }
                break;
            default:
                break;
        }
    }

    private void changeRadio() {
        if (isMan) {
            imgMan.setImageResource(R.drawable.ic_radio_select);
            imgWoman.setImageResource(R.drawable.ic_radio_normal);
        } else {
            imgMan.setImageResource(R.drawable.ic_radio_normal);
            imgWoman.setImageResource(R.drawable.ic_radio_select);
        }
    }
}
