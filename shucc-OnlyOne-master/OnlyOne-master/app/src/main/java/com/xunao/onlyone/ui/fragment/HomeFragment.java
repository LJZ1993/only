package com.xunao.onlyone.ui.fragment;

import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.activity.BeginnerGuideActivity;
import com.xunao.onlyone.ui.activity.GoodAreaActivity;
import com.xunao.onlyone.ui.activity.SearchActivity;
import com.xunao.onlyone.ui.activity.SetLocationActivity;
import com.xunao.onlyone.ui.adapter.ImageLoopAdapter;
import com.xunao.onlyone.ui.adapter.HomeFragmentAdapter;
import com.xunao.onlyone.ui.adapter.LoadMoreAdapter;
import com.xunao.onlyone.ui.base.BaseFragment;
import com.xunao.onlyone.ui.decoration.DividerGridItemDecoration;
import com.xunao.onlyone.ui.widget.AdvertisingPoint;
import com.xunao.onlyone.ui.widget.MyPtrClassicFrameLayout;
import com.xunao.onlyone.util.ScreenUtil;
import com.xunao.onlyone.model.Good;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    //定位地址
    @BindView(R.id.text_location)
    TextView textLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;

    @BindView(R.id.edit_search)
    EditText editSearch;

    //广告
    @BindView(R.id.loop_advertising)
    ViewPager loopAdvertising;
    //包裹广告下方小红点
    @BindView(R.id.ll_point)
    LinearLayout llPoint;
    @BindView(R.id.rl_advertising)
    RelativeLayout rlAdvertising;

    @BindView(R.id.ll_beginner_guide)
    LinearLayout llBeginnerGuide;
    @BindView(R.id.ll_ten_yuan)
    LinearLayout llTenYuan;

    @BindView(R.id.ll_show)
    LinearLayout llShow;
    @BindView(R.id.ll_sign_in)
    LinearLayout llSignIn;
    @BindView(R.id.ll_guide)
    LinearLayout llGuide;

    @BindView(R.id.img_hot)
    ImageView imgHot;
    @BindView(R.id.text_hot)
    TextView textHot;
    @BindView(R.id.rl_hot)
    RelativeLayout rlHot;

    @BindView(R.id.img_progress)
    ImageView imgProgress;
    @BindView(R.id.text_progress)
    TextView textProgress;
    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;

    @BindView(R.id.img_latest)
    ImageView imgLatest;
    @BindView(R.id.text_latest)
    TextView textLatest;
    @BindView(R.id.rl_latest)
    RelativeLayout rlLatest;

    @BindView(R.id.img_need)
    ImageView imgNeed;
    @BindView(R.id.text_need)
    TextView textNeed;
    @BindView(R.id.rl_need)
    RelativeLayout rlNeed;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;

    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingLayout;

    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.ptr_layout)
    MyPtrClassicFrameLayout ptrLayout;
    
    @BindViews({R.id.img_hot, R.id.img_progress, R.id.img_latest, R.id.img_need})
    List<ImageView> imgSortList;
    @BindViews({R.id.text_hot, R.id.text_progress, R.id.text_latest, R.id.text_need})
    List<TextView> textSortList;

    //移动距离
    private int offset = 0;
    private GridLayoutManager gridLayoutManager;
    private HomeFragmentAdapter adapter;
    //商品列表数据
    private List<Good> mData;
    
    //正常排序类型图标
    private final SparseIntArray drawableNormalMap = new SparseIntArray() {
        {
            put(0, R.drawable.ic_home_hot_normal);
            put(1, R.drawable.ic_home_progress_normal);
            put(2, R.drawable.ic_home_latest_normal);
            put(3, R.drawable.ic_home_need_normal);
        }
    };
    //当前排序类型选中图标
    private final SparseIntArray drawableSelectMap = new SparseIntArray() {
        {
            put(0, R.drawable.ic_home_hot_select);
            put(1, R.drawable.ic_home_progress_select);
            put(2, R.drawable.ic_home_latest_select);
            put(3, R.drawable.ic_home_need_select);
        }
    };
    
    //之前选中的排序类型
    private int preSortSelect = -1;
    //当前选中排序类型
    private int nowSortSelect = 0;

    //广告地址列表
    private List<String> imgUrlList;
    //广告小圆点
    private List<AdvertisingPoint> advertisingPointList;
    //之前显示的广告
    private int preAdvertisingSelect = -1;
    //当前显示广告
    private int nowAdvertisingSelect = 0;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        //设置显示地址
        textLocation.setText(App.getSpUtil().getCity());
        collapsingLayout.setTitleEnabled(false);
        changeSort();

        //设置循环广告图片高度
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlAdvertising.getLayoutParams();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = ScreenUtil.width(baseActivity).px * 315 / 750;
        rlAdvertising.setLayoutParams(params);

        //TODO
        mData = new ArrayList<>();
        imgUrlList = new ArrayList<>();
        advertisingPointList = new ArrayList<>();
        imgUrlList.add("http://p1.meituan.net/movie/f1e42208897d8674bb7aab89fb078baf487236.jpg");
        imgUrlList.add("http://p1.meituan.net/movie/aa3c2bac8f9aaa557e63e20d56e214dc192471.jpg");
        imgUrlList.add("http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg");
        for (int i = 0; i < imgUrlList.size(); i++) {
            advertisingPointList.add(new AdvertisingPoint(baseActivity, llPoint, i == 0 ? true : false));
        }
        changeAdvertisingPoint(nowAdvertisingSelect);
        ImageLoopAdapter imageLoopAdapter = new ImageLoopAdapter(loopAdvertising, imgUrlList);
        loopAdvertising.setAdapter(imageLoopAdapter);
        loopAdvertising.setCurrentItem(imageLoopAdapter.getRealCurrentItem(nowAdvertisingSelect));
        imageLoopAdapter.setOnPageSelectd(new ImageLoopAdapter.OnPageSelectd() {
            @Override
            public void onPageSelected(int position) {
                changeAdvertisingPoint(position);
            }
        });

        getData();
    }

    @Override
    protected void bindEvent() {

        llLocation.setOnClickListener(this);

        editSearch.setOnClickListener(this);

        llBeginnerGuide.setOnClickListener(this);
        llTenYuan.setOnClickListener(this);
        llShow.setOnClickListener(this);
        llSignIn.setOnClickListener(this);

        rlHot.setOnClickListener(new SortClickListener(0));
        rlProgress.setOnClickListener(new SortClickListener(1));
        rlLatest.setOnClickListener(new SortClickListener(2));
        rlNeed.setOnClickListener(new SortClickListener(3));

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
                page = 1;
                if (adapter != null) {
                    adapter.setLoadAll(false);
                }
                getData();
            }
        });
        //显示时间
        ptrLayout.setLastUpdateTimeRelateObject(this);
        //viewpager滑动时禁用下拉
        ptrLayout.disableWhenHorizontalMove(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_location:
                openActivity(SetLocationActivity.class);
                break;
            case R.id.edit_search:  //搜索
                SearchActivity.launch(baseActivity);
                break;
            case R.id.ll_beginner_guide:     //新手指南
                BeginnerGuideActivity.launch(baseActivity);
                break;
            case R.id.ll_ten_yuan:   //十元专区
                openActivity(GoodAreaActivity.class);
                break;
            case R.id.ll_show:  //晒单
                break;
            case R.id.ll_sign_in:    //今日签到
                break;
            default:
                break;
        }
    }

    /**
     * 商品列表数据显示
     */
    private void showData() {
        if (adapter == null) {
            gridLayoutManager = new GridLayoutManager(baseActivity, 2);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);

            adapter = new HomeFragmentAdapter(baseActivity, recyclerView, new LoadMoreAdapter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    page++;
                    getData();
                }
            }, mData);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerGridItemDecoration(baseActivity));
            adapter.setOnItemClickListener(new LoadMoreAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                }
            });
        }
        //全部加载完成
        if (page == 3) {
            adapter.setLoadAll(true);
        } else {
            adapter.reset();
        }
        if (ptrLayout.isShown()) {
            ptrLayout.refreshComplete();
        }
    }

    //TODO 测试数据
    private int page = 1;
    private Handler handler = new Handler();

    private void getData() {
        if (page == 1) {
            mData.clear();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int size = mData.size();
                for (int i = size; i < size + 20; i++) {
                    Good good = new Good();
                    good.setGoodUrl("http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg");
                    good.setGoodName("Apple iphone SE" + i);
                    good.setGoodTotal(100 + i * i * 50);
                    good.setGoodOverage(32 + i * 32);
                    mData.add(good);
                }
                showData();
            }
        }, 1000);
    }

    /**
     * 修改排序类型图标显示
     */
    private void changeSort() {
        if (preSortSelect != -1) {
            imgSortList.get(preSortSelect).setImageResource(drawableNormalMap.get(preSortSelect));
            textSortList.get(preSortSelect).setTextColor(getResources().getColor(R.color.sort_normal));
        }
        imgSortList.get(nowSortSelect).setImageResource(drawableSelectMap.get(nowSortSelect));
        textSortList.get(nowSortSelect).setTextColor(getResources().getColor(R.color.sort_select));
        //TODO 排序列表根据排序类型刷新
    }

    /**
     * 修改循环广告下方小圆点
     *
     * @param position
     */
    private void changeAdvertisingPoint(int position) {
        preAdvertisingSelect = nowAdvertisingSelect;
        nowAdvertisingSelect = position;
        if (preAdvertisingSelect != -1) {
            advertisingPointList.get(preAdvertisingSelect).setFocus(false);
        }
        if (nowAdvertisingSelect != -1) {
            advertisingPointList.get(nowAdvertisingSelect).setFocus(true);
        }
    }

    /**
     * 排序类型选中事件
     */
    private class SortClickListener implements View.OnClickListener {

        private int position;

        public SortClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (position != nowSortSelect) {
                preSortSelect = nowSortSelect;
                nowSortSelect = position;
                changeSort();
            }
        }
    }
}
