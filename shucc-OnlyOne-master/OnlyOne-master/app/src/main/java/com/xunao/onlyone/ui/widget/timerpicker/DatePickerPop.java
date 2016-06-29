package com.xunao.onlyone.ui.widget.timerpicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.xunao.onlyone.R;
import com.xunao.onlyone.util.Debug;
import com.xunao.onlyone.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenchao on 16/6/7.
 * cc@cchao.org
 */
public class DatePickerPop extends PopupWindow {

    private Context context;
    
    private View rootView;

    private LinearLayout llParent;
    private RelativeLayout rlPop;
    private int leftPadding = -1;
    private int rightPadding = -1;

    private LoopView lpYear;
    private LoopView lpMonth;
    private LoopView lpDay;

    private View firstLine;
    private View secondLine;

    private int yearPos = 0;
    private int monthPos = 0;
    private int dayPos = 0;
    private List<String> dataYear;
    private List<String> dataMonth;
    private List<String> dataDay;

    private final int minYear = 1900;

    private OnDatePickerListener onDatePickerListener;
    
    public DatePickerPop(Context context, int year, int month, int day) {
        this.context = context;

        yearPos = year - minYear;
        monthPos = month - 1;
        dayPos = day - 1;

        bindView();
        initData();

        setTouchable(true);
        setFocusable(true);
        // setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(rootView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setOnDatePickerListener(OnDatePickerListener onDatePickerListener) {
        this.onDatePickerListener = onDatePickerListener;
    }

    private void bindView() {
        rootView = LayoutInflater.from(context).inflate(R.layout.pop_date_picker, null);
        llParent = (LinearLayout) rootView.findViewById(R.id.pop_date_picker_parent);
        rlPop = (RelativeLayout) rootView.findViewById(R.id.pop_date_picker_parent_rl);
        firstLine = rootView.findViewById(R.id.pop_date_picker_line_first);
        secondLine = rootView.findViewById(R.id.pop_date_picker_line_second);
        lpYear = (LoopView) rootView.findViewById(R.id.pop_date_picker_year);
        lpMonth = (LoopView) rootView.findViewById(R.id.pop_date_picker_month);
        lpDay = (LoopView) rootView.findViewById(R.id.pop_date_picker_day);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopWin();
            }
        });
    }

    private void initData() {
        dataYear = new ArrayList<>();
        dataMonth = new ArrayList<>();
        dataDay = new ArrayList<>();

        for (int i = minYear; i < 2020; i++) {
            dataYear.add(i + "年");
        }
        for (int i = 0; i < 12; i++) {
            dataMonth.add((i + 1) + "月");
        }
        for (int i = 0; i < 31; i++) {
            dataDay.add((i + 1) + "日");
        }
        lpYear.setItems(dataYear);
        lpMonth.setItems(dataMonth);
        lpDay.setItems(dataDay);

        lpYear.setInitPosition(yearPos);
        lpMonth.setInitPosition(monthPos);
        lpDay.setInitPosition(dayPos);

        lpMonth.setViewPadding(ScreenUtil.dipToPx(context, 16), 0, ScreenUtil.dipToPx(context, 16), 0);

        lpYear.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                yearPos = index;
                initDay();
                onItemChange();
            }
        });
        //画横线
        lpYear.setOnLineListener(new LoopView.OnLineListener() {
            @Override
            public void setLine(int firstY, int secondY) {
                ViewGroup.MarginLayoutParams firstParam = (ViewGroup.MarginLayoutParams) firstLine.getLayoutParams();
                firstParam.topMargin = firstY;
                firstLine.setLayoutParams(firstParam);
                ViewGroup.MarginLayoutParams secondParam = (ViewGroup.MarginLayoutParams) secondLine.getLayoutParams();
                secondParam.topMargin = secondY;
                secondLine.setLayoutParams(secondParam);
            }
        });
        lpMonth.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                monthPos = index;
                initDay();
                onItemChange();
            }
        });
        lpDay.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                dayPos = index;
                onItemChange();
            }
        });
        lpDay.setDayChangeListener(new LoopView.DayChangeListener() {
            @Override
            public void onDayChange(int selectItem) {
                if (dayPos != selectItem) {
                    dayPos = selectItem;
                    onItemChange();
                }
            }
        });
    }

    public void showPopWin(Activity activity) {

        if (null != activity) {

            TranslateAnimation trans = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
                    0, Animation.RELATIVE_TO_SELF, 1,
                    Animation.RELATIVE_TO_SELF, 0);

            showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM,
                    0, 0);
            trans.setDuration(400);
            trans.setInterpolator(new AccelerateDecelerateInterpolator());

            llParent.startAnimation(trans);
        }
    }

    public void dismissPopWin() {

        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateInterpolator());
        trans.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                dismiss();
            }
        });

        llParent.startAnimation(trans);
    }

    private void initDay() {
        int daysize = 0;
        int year = yearPos + minYear;
        int month = monthPos + 1;
        if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                daysize = 29;
            } else {
                daysize = 28;
            }
        } else if (month == 1 || month == 3 || month == 5 ||
                month == 7 || month == 8 || month == 10 || month == 12) {
            daysize = 31;
        } else {
            daysize = 30;
        }
        dataDay.clear();
        for (int i = 0; i < daysize; i++) {
            dataDay.add((i + 1) + "日");
        }
        lpDay.setItems(dataDay);
        lpDay.setInitPosition(dayPos);
    }

    private void onItemChange() {
        if (dayPos + 1 > dataDay.size()) {
            dayPos = dataDay.size() - 1;
        }
        if (onDatePickerListener != null) {
            onDatePickerListener.OnItemSelect(minYear + yearPos, 1 + monthPos, 1 + dayPos
                    , dataYear.get(yearPos) + dataMonth.get(monthPos) + dataDay.get(dayPos));
        }
    }

    public interface OnDatePickerListener {
        void OnItemSelect(int year, int month, int day, String result);
    }
}
