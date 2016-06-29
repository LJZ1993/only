package com.xunao.onlyone.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chenchao on 16/6/6.
 * cc@cchao.org
 */
public class CommonUtil {

    /**
     * 获取 客户端版本号
     *
     * @return
     */
    public static String getCurVersion(Context context) {
        String curVersion = "";
        try {
            curVersion = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return curVersion;
    }

    /*
     * 判断MOBILE网络是否可用
     */
    public static boolean isGPRSConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /*
     * 判断wifi网络是否可用
     */
    boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (!ni.getTypeName().equals("WIFI")) {
				/*
				 * ni.getTypeNmae()可能取值如下 WIFI，表示WIFI联网 MOBILE，表示GPRS、EGPRS
				 * 3G网络没有测试过 WIFI和(E)GPRS不能共存，如果两个都打开，系统仅支持WIFI
				 */
                return true;
            }
        }
        return false;
    }

}
