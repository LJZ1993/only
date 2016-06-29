package com.xunao.onlyone.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by chenchao on 16/6/7.
 * cc@cchao.org
 */
public class SPUtil {

    private final String TAG = "SPUtil";

    private final String ONLY_ONE_DATA = "only_one_data";

    //性别
    private final String GENDER_KEY = "gender";

    //昵称
    private final String NICK_NAME = "nick_name";

    //手机号码
    private final String TEL_KEY = "tel_num";

    //生日年月日
    private final String YEAR_KEY = "year";
    private final String MONTH_KEY = "month";
    private final String DAY_KEY = "day";

    private final String CITY_KEY = "city_name";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SPUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(ONLY_ONE_DATA, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    private void save(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    private void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    private String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    private int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    private boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveYear(int year) {
        save(YEAR_KEY, year);
    }

    public int getYear() {
        return getInt(YEAR_KEY);
    }

    public void saveMonth(int month) {
        save(MONTH_KEY, month);
    }

    public int getMonth() {
        return getInt(MONTH_KEY);
    }

    public void saveDay(int day) {
        save(DAY_KEY, day);
    }

    public int getDay() {
        return getInt(DAY_KEY);
    }

    public void saveGender(boolean isMan) {
        save(GENDER_KEY, isMan ? "男" : "女");
    }

    public String getGender() {
        if (TextUtils.isEmpty(getString(GENDER_KEY)))
            return "男";
        return getString(GENDER_KEY);
    }

    public void saveNickName(String nickName) {
        save(NICK_NAME, nickName);
    }

    public String getNickName() {
        return getString(NICK_NAME);
    }

    public void saveTel(String tel) {
        save(TEL_KEY, tel);
    }

    public String getTel() {
        return getString(TEL_KEY);
    }

    public void saveCity(String name) {
        save(CITY_KEY, name);
    }

    public String getCity() {
        return getString(CITY_KEY);
    }
}
