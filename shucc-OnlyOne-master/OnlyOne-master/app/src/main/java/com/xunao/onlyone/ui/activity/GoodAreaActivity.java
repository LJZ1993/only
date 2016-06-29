package com.xunao.onlyone.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.widget.MyPtrClassicFrameLayout;
import com.xunao.onlyone.util.ScreenUtil;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by chenchao on 16/6/17.
 * cc@cchao.org
 * 专区
 */
public class GoodAreaActivity extends BaseActivity {

    //顶部
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_acionbar_title)
    TextView textAcionbarTitle;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.text_cart_num)
    TextView textCartNum;

    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout ptrLayout;

    //移动距离
    private int offset = 0;

    @Override
    protected int bindLayout() {
        return R.layout.activity_good_area;
    }

    @Override
    protected void initData() {
        textAcionbarTitle.setText(getResources().getString(R.string.goodareaactivity_acionbar_title));
        setImgBack(imgBack);
        collapsingLayout.setTitleEnabled(false);

        //设置循环广告图片高度
        ViewGroup.LayoutParams params = imgHead.getLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = ScreenUtil.width(this).px * 315 / 750;
        imgHead.setLayoutParams(params);

        //TODO 测试
        ImageLoader.getInstance().displayImage("http://p1.meituan.net/movie/f1e42208897d8674bb7aab89fb078baf487236.jpg",
                imgHead);
    }

    @Override
    protected void bindEvent() {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                offset = verticalOffset;
            }
        });
        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return offset == 0 && PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            }
        });
        //显示时间
        ptrLayout.setLastUpdateTimeRelateObject(this);
        //viewpager滑动时禁用下拉
        ptrLayout.disableWhenHorizontalMove(true);
    }
}
