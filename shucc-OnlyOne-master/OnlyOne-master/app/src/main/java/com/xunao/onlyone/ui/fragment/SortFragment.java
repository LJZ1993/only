package com.xunao.onlyone.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.xunao.onlyone.R;
import com.xunao.onlyone.model.Category;
import com.xunao.onlyone.model.Team;
import com.xunao.onlyone.ui.activity.SearchActivity;
import com.xunao.onlyone.ui.adapter.RecommendLeftAdapter;
import com.xunao.onlyone.ui.adapter.RecommendRightAdapter;
import com.xunao.onlyone.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenchao on 16/5/27.
 * cc@cchao.org
 * 分类
 */
public class SortFragment extends BaseFragment {
    //控件的初始化
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.listView_recommend)
    ListView listRecommend;
    @BindView(R.id.recyclerview_all)
    RecyclerView recyclerviewAll;
    public static int selectItem;
    private GridLayoutManager gridLayoutManager;
    private List<Category> categoryList;
    private static int oldSelectedPostion = 0;
    private RecommendRightAdapter recommendRightAdapter;
    private RecommendLeftAdapter recommendLeftAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initData() {
        getLeftAndRightData();
        //recommend列表的展示 由于只有一行且数据显示方式简单，使用ListView控件
        showRecommendLeftData();
        //右边的产品详细展示
        showRecommendRightData();

    }

    private void getLeftAndRightData() {
        categoryList = new ArrayList<>();
        List<Team> teamList1 = new ArrayList<>();
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList1.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        Category category1 = new Category(teamList1, "数码产1");
        category1.setSelect(true);
        List<Team> teamList2 = new ArrayList<>();
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        teamList2.add(new Team("手机", "http://img3.imgtn.bdimg.com/it/u=479294436,1113966969&fm=21&gp=0.jpg"));
        Category category2 = new Category(teamList2, "数码产2");
        Category category3 = new Category(teamList2, "数码产3");
        Category category4 = new Category(teamList2, "数码产4");
        Category category5 = new Category(teamList2, "数码产5");
        Category category6 = new Category(teamList2, "数码产6");
        Category category7 = new Category(teamList2, "数码产2");
        Category category8= new Category(teamList2, "数码产3");
        Category category9 = new Category(teamList2, "数码产4");
        Category category10 = new Category(teamList2, "数码产5");
        Category categorya = new Category(teamList2, "数码产6");
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);
        categoryList.add(category6);
        categoryList.add(category7);
        categoryList.add(category8);
        categoryList.add(category9);
        categoryList.add(category10);
        categoryList.add(categorya);





    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showRecommendRightData() {
        //先获取适配器
        recommendRightAdapter = new RecommendRightAdapter(baseActivity, categoryList);
        gridLayoutManager = new GridLayoutManager(baseActivity, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerviewAll.setLayoutManager(gridLayoutManager);
        recommendRightAdapter.setOnItemClickListener(baseActivity);
        recyclerviewAll.addItemDecoration(new StickyRecyclerHeadersDecoration(recommendRightAdapter));
//        recyclerviewAll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                int first = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
//                int last = gridLayoutManager.findLastCompletelyVisibleItemPosition();
//                //避免点击左侧最后一个无响应
//                //if里面的条件并不知道是什么意思
//                if (last!=gridLayoutManager.getItemCount()-1){
//                    int categoryType = recommendRightAdapter.getCategoryType(first);
//                    changeSelect(categoryType);
//                }else {
//                    changeSelect(recommendRightAdapter.getItemCount()-1);
//                }
//            }
//        });

    }

    private void changeSelect(int position) {
        //前选中的状态取消
        categoryList.get(oldSelectedPostion).setSelect(false);
        categoryList.get(position).setSelect(true);
        oldSelectedPostion = position;
        recommendRightAdapter.notifyDataSetChanged();
    }

    private void showRecommendLeftData() {
        recommendLeftAdapter = new RecommendLeftAdapter(baseActivity, categoryList);
        listRecommend.setAdapter(recommendLeftAdapter);
        listRecommend.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem = position;
                recommendLeftAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    protected void bindEvent() {
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SearchActivity.class);
            }
        });
    }
}
