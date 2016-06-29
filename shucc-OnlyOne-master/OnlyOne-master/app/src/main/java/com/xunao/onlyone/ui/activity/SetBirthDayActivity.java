package com.xunao.onlyone.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.widget.timerpicker.DatePickerPop;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/6/7.
 * cc@cchao.org
 * 设定生日
 */
public class SetBirthDayActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_birthday_now)
    TextView textBirthDay;

    private DatePickerPop datePickerPop;

    //生日年月日
    private int nowYear = 1990;
    private int nowMonth = 3;
    private int nowDay = 31;

    @Override
    protected int bindLayout() {
        return R.layout.activity_set_birthday;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);

        if (App.getSpUtil().getYear() != -1) {
            nowYear = App.getSpUtil().getYear();
            nowMonth = App.getSpUtil().getMonth();
            nowDay = App.getSpUtil().getDay();
            textBirthDay.setText(nowYear + "年" + nowMonth + "月" + nowDay + "日");
        }

        datePickerPop = new DatePickerPop(this, nowYear, nowMonth, nowDay);
        datePickerPop.setOnDatePickerListener(new DatePickerPop.OnDatePickerListener() {
            @Override
            public void OnItemSelect(int year, int month, int day, String result) {
                nowYear = year;
                nowMonth = month;
                nowDay = day;
                textBirthDay.setText(result);
            }
        });
    }

    @Override
    protected void bindEvent() {
    }

    @OnClick(R.id.text_ok) void save() {
        App.getSpUtil().saveYear(nowYear);
        App.getSpUtil().saveMonth(nowMonth);
        App.getSpUtil().saveDay(nowDay);
        finish();
    }

    @OnClick(R.id.text_birthday_now) void showDatePicker() {
        if (datePickerPop != null && datePickerPop.isShowing()) {
            datePickerPop.dismissPopWin();
        } else if (datePickerPop != null) {
            datePickerPop.showPopWin(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (datePickerPop != null && datePickerPop.isShowing()) {
            datePickerPop.dismissPopWin();
        } else {
            super.onBackPressed();
        }
    }
}
