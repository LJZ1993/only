package com.xunao.onlyone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.util.Debug;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenchao on 16/6/7.
 * cc@cchao.org
 * 我的资料
 */
public class MineInfoActivity extends BaseActivity implements View.OnClickListener {

    private final int SET_BIRTHDAY_RESULT = 100;
    private final int SET_GENDER_RESULT = 200;
    private final int SET_NICK_NAME_RESULT = 300;
    private final int SET_TEL_RESULT = 400;
    private final int SET_CITY_RESULT = 500;

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_avator)
    CircleImageView imgAvator;
    @BindView(R.id.text_id)
    TextView textID;
    @BindView(R.id.rl_id)
    RelativeLayout rlID;
    @BindView(R.id.text_nick)
    TextView textNick;
    @BindView(R.id.rl_nick)
    RelativeLayout rlNick;
    @BindView(R.id.text_tel)
    TextView textTel;
    @BindView(R.id.rl_tel)
    RelativeLayout rlTel;
    @BindView(R.id.text_gender)
    TextView textGender;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.text_birthday)
    TextView textBirthDay;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthDay;
    @BindView(R.id.text_city)
    TextView textCity;
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.rl_addr)
    RelativeLayout rlRewardAddr;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWX;
    @BindView(R.id.rl_sina)
    RelativeLayout rlSina;
    @BindView(R.id.rl_QQ)
    RelativeLayout rlQQ;
    @BindView(R.id.rl_alter_password)
    RelativeLayout rlAlterPassword;
    @BindView(R.id.text_wx)
    TextView textWX;
    @BindView(R.id.text_sina)
    TextView textSina;
    @BindView(R.id.text_QQ)
    TextView textQQ;

    @Override
    protected int bindLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
        setNickName();
        setGender();
        setBirthDay();
        setTel();
        setCity();
    }

    @Override
    protected void bindEvent() {
        rlID.setOnClickListener(this);
        rlNick.setOnClickListener(this);
        rlTel.setOnClickListener(this);
        rlGender.setOnClickListener(this);
        rlBirthDay.setOnClickListener(this);
        rlCity.setOnClickListener(this);
        rlRewardAddr.setOnClickListener(this);
        rlWX.setOnClickListener(this);
        rlSina.setOnClickListener(this);
        rlQQ.setOnClickListener(this);
        rlAlterPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_tel:
                openActivity(SetTelActivity.class, SET_TEL_RESULT);
                break;
            case R.id.rl_nick:
                openActivity(SetNickNameActivity.class, SET_NICK_NAME_RESULT);
                break;
            case R.id.rl_birthday:
                openActivity(SetBirthDayActivity.class, SET_BIRTHDAY_RESULT);
                break;
            case R.id.rl_gender:
                openActivity(SetGenderActivity.class, SET_GENDER_RESULT);
                break;
            case R.id.rl_alter_password:
                openActivity(SetPasswordActivity.class);
                break;
            case R.id.rl_city:
                openActivity(SetLocationActivity.class, SET_CITY_RESULT);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SET_BIRTHDAY_RESULT:
                setBirthDay();
                break;
            case SET_GENDER_RESULT:
                setGender();
                break;
            case SET_NICK_NAME_RESULT:
                setNickName();
                break;
            case SET_TEL_RESULT:
                setTel();
                break;
            case SET_CITY_RESULT:
                setCity();
                break;
            default:
                break;
        }
    }

    /**
     * 设置地址
     */
    private void setCity() {
        textCity.setText(App.getSpUtil().getCity());
    }

    /**
     * 设置手机号码
     */
    private void setTel() {
        textTel.setText(App.getSpUtil().getTel());
    }

    /**
     * 设定昵称
     */
    private void setNickName() {
        textNick.setText(App.getSpUtil().getNickName());
    }

    /**
     * 设置性别
     */
    private void setGender() {
        String gender = App.getSpUtil().getGender();
        if (!textGender.getText().toString().equals(gender)) {
            textGender.setText(gender);
        }
    }

    /**
     * 设置生日显示
     */
    private void setBirthDay() {
        if (App.getSpUtil().getYear() != -1) {
            int nowYear = App.getSpUtil().getYear();
            int nowMonth = App.getSpUtil().getMonth();
            int nowDay = App.getSpUtil().getDay();
            textBirthDay.setText(nowYear + ""
                    + (nowMonth < 10 ? ("0" + nowMonth) : nowMonth) + ""
                    + (nowDay < 10 ? ("0" + nowDay) : nowDay) + "");
        }
    }
}
