package com.xunao.onlyone.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.widget.FlowLayout;
import com.xunao.onlyone.util.ListUtil;
import com.xunao.onlyone.util.ScreenUtil;
import com.xunao.onlyone.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/6/3.
 * cc@cchao.org
 * 搜索页面
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.flow_history)
    FlowLayout flowHistory;
    //暂无历史搜索
    @BindView(R.id.text_history_null)
    TextView textHistoryNull;
    @BindView(R.id.flow_hot)
    FlowLayout flowHot;
    //暂无热门搜索
    @BindView(R.id.text_hot_null)
    TextView textHotNull;

    //历史搜索
    private List<String> historyData;
    //热门搜索
    private List<String> hotData;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        setImgBack(imgBack);
        historyData = new ArrayList<>();
        hotData = new ArrayList<>();

        //TODO 测试数据
        hotData.add("防晒衣");
        hotData.add("手机");
        hotData.add("短裙");
        hotData.add("情侣装");
        hotData.add("皮鞋");
        hotData.add("男士T恤短袖");
        hotData.add("皮包");
        hotData.add("高跟鞋");
        hotData.add("牛仔短裤");
        hotData.add("夏季套装");

        if (ListUtil.isEmpty(historyData)) {
            flowHistory.setVisibility(View.GONE);
            textHistoryNull.setVisibility(View.VISIBLE);
        } else {
            flowHistory.setVisibility(View.VISIBLE);
            textHistoryNull.setVisibility(View.GONE);

            for (String title : historyData) {
                TextView textView = getTagItem(flowHistory, title);
            }
        }

        if (ListUtil.isEmpty(hotData)) {
            flowHot.setVisibility(View.GONE);
            textHotNull.setVisibility(View.VISIBLE);
        } else {
            flowHot.setVisibility(View.VISIBLE);
            textHotNull.setVisibility(View.GONE);

            for (String title : hotData) {
                TextView textView = getTagItem(flowHot, title);
            }
        }
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 获取Item
     *
     * @param parent
     * @param title
     * @return
     */
    private TextView getTagItem(FlowLayout parent, String title) {
        int margin = ScreenUtil.dipToPx(this, 8);
        TextView textView = new TextView(SearchActivity.this);
        textView.setText(title);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_search_item));
        textView.setPadding(margin << 1, margin, margin << 1, margin);

        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT
                , FlowLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = margin;
        layoutParams.bottomMargin = margin;
        parent.addView(textView, layoutParams);

        textView.setOnClickListener(new TagItemClickListener(title));

        return textView;
    }

    private class TagItemClickListener implements View.OnClickListener {

        private String title;

        public TagItemClickListener(String title) {
            this.title = title;
        }

        @Override
        public void onClick(View v) {
            ToastUtil.Infotoast(SearchActivity.this, title);
        }
    }
}
