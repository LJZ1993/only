package com.xunao.onlyone.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xunao.onlyone.R;
import com.xunao.onlyone.util.ScreenUtil;

/**
 * Created by chenchao on 16/6/1.
 * cc@cchao.org
 * 循环图片小圆点
 */
public class AdvertisingPoint {

    private Context context;
    private View point;

    public AdvertisingPoint(Context context, LinearLayout parent, boolean isFirst) {
        this.context = context;
        point = new View(context);
        point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_normal));
        parent.addView(point);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) point.getLayoutParams();
        params.width = ScreenUtil.dipToPx(context, 5);
        params.height = ScreenUtil.dipToPx(context, 5);
        if (!isFirst) {
            params.leftMargin = ScreenUtil.dipToPx(context, 8);
        }
        point.setLayoutParams(params);
    }

    public void setFocus(boolean isFocus) {
        if (isFocus) {
            point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_select));
        } else {
            point.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_point_normal));
        }
    }

}
