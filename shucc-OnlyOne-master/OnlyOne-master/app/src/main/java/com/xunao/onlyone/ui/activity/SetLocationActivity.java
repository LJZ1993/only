package com.xunao.onlyone.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xunao.onlyone.App;
import com.xunao.onlyone.R;
import com.xunao.onlyone.model.City;
import com.xunao.onlyone.ui.adapter.CityAllAdapter;
import com.xunao.onlyone.ui.adapter.CitySearchAdapter;
import com.xunao.onlyone.ui.adapter.LoadMoreAdapter;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.dialog.LoadingDialog;
import com.xunao.onlyone.ui.widget.SideLetterBar;
import com.xunao.onlyone.util.ListUtil;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenchao on 16/6/20.
 * cc@cchao.org
 * 设置地址
 */
public class SetLocationActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.img_clean)
    ImageView imgClean;
    @BindView(R.id.text_search)
    TextView textSearch;
    @BindView(R.id.listview_all_city)
    ListView listviewAllCity;
    @BindView(R.id.side_letter_bar)
    SideLetterBar sideLetterBar;
    @BindView(R.id.text_letter_overlay)
    TextView textLetterOverlay;
    @BindView(R.id.recyclerview_search)
    RecyclerView recyclerviewSearch;
    @BindView(R.id.emptyView)
    TextView emptyView;
    private CitySearchAdapter citySearchAdapter;

    //所有城市
    private List<City> cityList;
    //最近访问城市
    private List<City> cityHistory;
    //热门城市
    private List<City> cityHot;

    private List<City> cityResult;

    private LoadingDialog loading;

    @Override
    protected int bindLayout() {
        return R.layout.activity_set_location;
    }

    @Override
    protected void initData() {

        setImgBack(imgBack);
        cityResult = new ArrayList<>();
        cityList = new ArrayList<>();
        cityHistory = new ArrayList<>();
        cityHot = new ArrayList<>();
        textTitle.setText(getResources().getString(R.string.setlocationactivity_title)
                + App.getSpUtil().getCity());

        SQLiteDatabase db = Connector.getDatabase();

        //TODO
        cityHistory.add(new City("上海", "0"));
        cityHistory.add(new City("武汉", "0"));
        cityHistory.add(new City("郑州", "0"));
        cityHot.add(new City("上海", "0"));
        cityHot.add(new City("乌鲁木齐", "0"));
        cityHot.add(new City("成都", "0"));
        cityHot.add(new City("阿拉善", "0"));
        cityHot.add(new City("广州", "0"));
        cityHot.add(new City("重庆", "0"));
        cityHot.add(new City("拉萨", "0"));

        Observable
                .create(new Observable.OnSubscribe<List<City>>() {
                    @Override
                    public void call(Subscriber<? super List<City>> subscriber) {
                        cityList = DataSupport.findAll(City.class);
                        subscriber.onNext(cityList);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        loading = LoadingDialog.show(SetLocationActivity.this, null);
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<List<City>, Boolean>() {
                    @Override
                    public Boolean call(List<City> cities) {
                        //数据库已保存数据，则进行数据展示
                        if (!ListUtil.isEmpty(cityList)) {
                            Collections.sort(cityList, new CityComparator());
                            showData();
                        }
                        return ListUtil.isEmpty(cityList);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<List<City>, Boolean>() {
                    @Override
                    public Boolean call(List<City> cities) {
                        //保存城市信息到数据库
                        cityList = loadJson();
                        DataSupport.saveAll(cityList);
                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .finallyDo(new Action0() {
                    @Override
                    public void call() {
                        loading.dismiss();
                    }
                })
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        showData();
                    }
                });
    }

    @Override
    protected void bindEvent() {
        imgClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSearch.setText("");
                hideKeyboard(editSearch);
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    imgClean.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    recyclerviewSearch.setVisibility(View.GONE);
                } else {
                    imgClean.setVisibility(View.VISIBLE);
                    recyclerviewSearch.setVisibility(View.VISIBLE);
                    cityResult.clear();
                    List<City> result = DataSupport.where("name like ? or pinyin like ?", keyword + "%", keyword + "%").find(City.class);
                    cityResult.addAll(result);
                    if (ListUtil.isEmpty(cityResult)) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        Collections.sort(cityResult, new CityComparator());
                        emptyView.setVisibility(View.GONE);
                        if (citySearchAdapter == null) {
                            LinearLayoutManager searchManager = new LinearLayoutManager(SetLocationActivity.this);
                            searchManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerviewSearch.setLayoutManager(searchManager);
                            citySearchAdapter = new CitySearchAdapter(cityResult);
                            recyclerviewSearch.setAdapter(citySearchAdapter);
                            citySearchAdapter.setOnItemClickListener(new LoadMoreAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    saveLocation(cityResult.get(position).getName());
                                }
                            });
                        } else {
                            citySearchAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    /**
     * 显示所有城市
     */
    private void showData() {

        final CityAllAdapter cityAllAdapter = new CityAllAdapter(SetLocationActivity.this, cityList, cityHistory, cityHot);
        listviewAllCity.setAdapter(cityAllAdapter);

        cityAllAdapter.setOnCityClickListener(new CityAllAdapter.OnCityClickListener() {
            @Override
            public void onCityListener(String name) {
                saveLocation(name);
            }
        });

        sideLetterBar.setOverlay(textLetterOverlay);
        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int pos = cityAllAdapter.getLetterPosition(letter);
                listviewAllCity.setSelection(pos);
            }
        });
    }

    /**
     * 保存选中地址到本地
     *
     * @param cityName
     */
    private void saveLocation(String cityName) {
        textTitle.setText(getResources().getString(R.string.setlocationactivity_title) + cityName);
        App.getSpUtil().saveCity(cityName);
    }

    /**
     * a-z排序
     */
    private class CityComparator implements Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }

    /**
     * 加载城市信息json字符串
     *
     * @return
     */
    private List<City> loadJson() {
        Gson gson = new Gson();
        List<City> cityList = new ArrayList<>();
        String json = null;
        try {
            InputStream is = getAssets().open("city.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            cityList = gson.fromJson(json, new TypeToken<List<City>>() {
            }.getType());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return cityList;
    }
}
