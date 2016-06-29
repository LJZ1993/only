package com.xunao.onlyone.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.adapter.RecommendGoodAdapter;
import com.xunao.onlyone.ui.base.BaseFragment;
import com.xunao.onlyone.util.ListUtil;
import com.xunao.onlyone.model.Good;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 购物车
 */
public class ShoppingCartFragment extends BaseFragment {

    //购物车为空
    @BindView(R.id.rl_not_data)
    RelativeLayout rlNotData;
    @BindView(R.id.recyclerview_recommend)
    RecyclerView recyclerviewRecommend;

    //购物车不为空
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    @BindView(R.id.text_edit)
    TextView textEdit;

    //购物车商品
    private List<Good> data;
    private LinearLayoutManager llManagerCart;

    //推荐商品
    private  List<Good> recommendData;
    private LinearLayoutManager llManagerRecommendGood;
    private RecommendGoodAdapter recommendGoodAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {

        data = new ArrayList<>();
        recommendData = new ArrayList<>();

        //TODO 测试数据
        for (int i = 0; i < 20; i++) {
            Good good = new Good();
            good.setGoodUrl("http://img3.imgtn.bdimg.com/it/u=1656497015,2313842836&fm=21&gp=0.jpg");
            good.setGoodName("Google Nexus系列" + i + "4G手机");
            good.setGoodTotal(100 + i * i * 50);
            good.setGoodOverage(32 + i * 32);
            recommendData.add(good);
        }

        if (ListUtil.isEmpty(data)) {
            rlNotData.setVisibility(View.VISIBLE);
            rlHaveData.setVisibility(View.GONE);
            initRecommend();
        } else {
            rlNotData.setVisibility(View.GONE);
            rlHaveData.setVisibility(View.VISIBLE);
            initShopCart();
        }
    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 初始化推荐商品列表
     */
    private void initRecommend() {
        llManagerRecommendGood = new LinearLayoutManager(baseActivity);
        llManagerRecommendGood.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewRecommend.setLayoutManager(llManagerRecommendGood);
        recommendGoodAdapter = new RecommendGoodAdapter(recommendData);
        recyclerviewRecommend.setAdapter(recommendGoodAdapter);
    }

    /**
     * 初始化我的购物车商品列表
     */
    private void initShopCart() {}

    /**
     * 立即夺宝
     */
    @OnClick(R.id.btn_immediately_indiana) void immediatelyIndianan() {}
}
