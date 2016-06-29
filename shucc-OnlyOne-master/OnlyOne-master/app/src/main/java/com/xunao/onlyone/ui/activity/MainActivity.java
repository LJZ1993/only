package com.xunao.onlyone.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.base.BaseFragment;
import com.xunao.onlyone.ui.fragment.AnnouncedFragment;
import com.xunao.onlyone.ui.fragment.HomeFragment;
import com.xunao.onlyone.ui.fragment.MineFragment;
import com.xunao.onlyone.ui.fragment.ShoppingCartFragment;
import com.xunao.onlyone.ui.fragment.SortFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_sort)
    LinearLayout llSort;
    @BindView(R.id.ll_announced)
    LinearLayout llAnnounced;
    @BindView(R.id.ll_shopping_cart)
    LinearLayout llShoppingCart;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;

    @BindViews({R.id.img_home, R.id.img_sort, R.id.img_announced, R.id.img_shopping_cart, R.id.img_mine})
    List<ImageView> imgTabMap;
    @BindViews({R.id.text_home, R.id.text_sort, R.id.text_announced, R.id.text_shopping_cart, R.id.text_mine})
    List<TextView> textTabMap;

    private HomeFragment homeFragment;
    private SortFragment sortFragment;
    private AnnouncedFragment announcedFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MineFragment mineFragment;

    private SparseArray<BaseFragment> fragmentTabMap = new SparseArray<>();
    //默认tab图标
    private final SparseIntArray drawableNormalMap = new SparseIntArray() {
        {
            put(0, R.drawable.ic_tab_one_normal);
            put(1, R.drawable.ic_tab_two_normal);
            put(2, R.drawable.ic_tab_three_normal);
            put(3, R.drawable.ic_tab_four_normal);
            put(4, R.drawable.ic_tab_five_normal);
        }
    };
    //选中tab图标
    private final SparseIntArray drawableSelectMap = new SparseIntArray() {
        {
            {
                put(0, R.drawable.ic_tab_one_select);
                put(1, R.drawable.ic_tab_two_select);
                put(2, R.drawable.ic_tab_three_select);
                put(3, R.drawable.ic_tab_four_select);
                put(4, R.drawable.ic_tab_five_select);
            }
        }
    };

    //之前选中tab
    private int preSelect = -1;
    //当前选中tab
    private int nowSelect = 0;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        changeTab();
    }

    @Override
    protected void bindEvent() {
        llHome.setOnClickListener(new TabClickListener(0));
        llSort.setOnClickListener(new TabClickListener(1));
        llAnnounced.setOnClickListener(new TabClickListener(2));
        llShoppingCart.setOnClickListener(new TabClickListener(3));
        llMine.setOnClickListener(new TabClickListener(4));
    }

    /**
     * 点击底部tab切换
     */
    private void changeTab() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (preSelect != -1) {
            imgTabMap.get(preSelect).setImageResource(drawableNormalMap.get(preSelect));
            textTabMap.get(preSelect).setTextColor(getResources().getColor(R.color.tab_normal));
            fragmentTransaction.hide(fragmentTabMap.get(preSelect));
        }
        imgTabMap.get(nowSelect).setImageResource(drawableSelectMap.get(nowSelect));
        textTabMap.get(nowSelect).setTextColor(getResources().getColor(R.color.tab_select));

        if (fragmentTabMap.get(nowSelect) == null) {
            switch (nowSelect) {
                case 0:
                    homeFragment = new HomeFragment();
                    fragmentTabMap.put(0, homeFragment);
                    fragmentTransaction.add(R.id.activity_main_frame, homeFragment);
                    break;
                case 1:
                    sortFragment = new SortFragment();
                    fragmentTabMap.put(1, sortFragment);
                    fragmentTransaction.add(R.id.activity_main_frame, sortFragment);
                    break;
                case 2:
                    announcedFragment = new AnnouncedFragment();
                    fragmentTabMap.put(2, announcedFragment);
                    fragmentTransaction.add(R.id.activity_main_frame, announcedFragment);
                    break;
                case 3:
                    shoppingCartFragment = new ShoppingCartFragment();
                    fragmentTabMap.put(3, shoppingCartFragment);
                    fragmentTransaction.add(R.id.activity_main_frame, shoppingCartFragment);
                    break;
                case 4:
                    mineFragment = new MineFragment();
                    fragmentTabMap.put(4, mineFragment);
                    fragmentTransaction.add(R.id.activity_main_frame, mineFragment);
                    break;
                default:
                    break;
            }
        } else {
            fragmentTransaction.show(fragmentTabMap.get(nowSelect));
        }
        fragmentTransaction.commit();
    }

    private class TabClickListener implements View.OnClickListener {

        private int position;

        public TabClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (position != nowSelect) {
                preSelect = nowSelect;
                nowSelect = position;
                changeTab();
            }
        }
    }
}
