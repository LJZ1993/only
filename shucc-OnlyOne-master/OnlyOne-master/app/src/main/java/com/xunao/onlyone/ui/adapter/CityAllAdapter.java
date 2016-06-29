package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.model.City;
import com.xunao.onlyone.ui.widget.FlowLayout;
import com.xunao.onlyone.util.ListUtil;
import com.xunao.onlyone.util.ScreenUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chenchao on 16/6/20.
 * cc@cchao.org
 */
public class CityAllAdapter extends BaseAdapter {

    private static final int VIEW_TYPE_COUNT = 4;

    private Context context;
    private LayoutInflater inflater;

    //所有城市数据
    private List<City> cityList;
    //最近访问城市
    private List<City> cityHistory;
    //热门城市
    private List<City> cityHot;

    private HashMap<String, Integer> letterIndexes;

    private OnCityClickListener onCityClickListener;

    public CityAllAdapter(Context context, List<City> cities, List<City> cityHistory, List<City> cityHot) {
        this.context = context;
        this.cityList = cities;
        this.cityHistory = cityHistory;
        this.cityHot = cityHot;
        inflater = LayoutInflater.from(context);

        cityList.add(0, new City("已定位城市：", "0"));
        cityList.add(1, new City("最近访问城市：", "1"));
        cityList.add(2, new City("热门城市：", "2"));
        int size = cityList.size();
        letterIndexes = new HashMap<>();
        for (int index = 0; index < size; index++){
            //当前城市拼音首字母
            String currentLetter = getFirstLetter(cityList.get(index).getPinyin());
            //上个首字母，如果不存在设为""
            String previousLetter = index >= 1 ? getFirstLetter(cityList.get(index - 1).getPinyin()) : "";
            if (!TextUtils.equals(currentLetter, previousLetter)){
                letterIndexes.put(currentLetter, index);
            }
        }
    }

    private String getFirstLetter(final String pinyin){
        String c = pinyin.substring(0, 1);
        return c.toUpperCase();
    }

    /**
     * 获取字母索引的位置
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter){
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public int getCount() {
        return cityList == null ? 0 : cityList.size();
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return position < VIEW_TYPE_COUNT - 1 ? position : VIEW_TYPE_COUNT - 1;
    }

    @Override
    public City getItem(int position) {
        return cityList == null ? null : cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityViewHolder holder;
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                convertView = inflater.inflate(R.layout.item_city_now, null);
                TextView textNow = (TextView) convertView.findViewById(R.id.text_now_location);
                //TODO
                textNow.setText("上海市");
                break;
            case 1:
                convertView = inflater.inflate(R.layout.item_city_history, null);
                if (!ListUtil.isEmpty(cityHistory)) {
                    FlowLayout flowHistory = (FlowLayout) convertView.findViewById(R.id.flow_history);
                    for (City city : cityHistory) {
                        setTagItem(flowHistory, city.getName());
                    }
                }
                break;
            case 2:
                convertView = inflater.inflate(R.layout.item_city_hot, null);
                if (!ListUtil.isEmpty(cityHot)) {
                    FlowLayout flowHot = (FlowLayout) convertView.findViewById(R.id.flow_hot);
                    for (City city : cityHot) {
                        setTagItem(flowHot, city.getName());
                    }
                }
                break;
            case 3:
                if (convertView == null){
                    convertView = inflater.inflate(R.layout.item_city_all, parent, false);
                    holder = new CityViewHolder();
                    holder.letter = (TextView) convertView.findViewById(R.id.text_letter);
                    holder.name = (TextView) convertView.findViewById(R.id.text_city_name);
                    convertView.setTag(holder);
                }else{
                    holder = (CityViewHolder) convertView.getTag();
                }

                final String cityName = cityList.get(position).getName();
                holder.name.setText(cityName);
                if (onCityClickListener != null) {
                    holder.name.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onCityClickListener.onCityListener(cityName);
                        }
                    });
                }
                String currentLetter = getFirstLetter(cityList.get(position).getPinyin());
                String previousLetter = position >= 1 ? getFirstLetter(cityList.get(position - 1).getPinyin()) : "";
                if (!TextUtils.equals(currentLetter, previousLetter)){
                    holder.letter.setVisibility(View.VISIBLE);
                    holder.letter.setText(currentLetter);
                }else{
                    holder.letter.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
        return convertView;
    }

    /**
     * 获取Item
     *
     * @param parent
     * @param title
     * @return
     */
    private void setTagItem(FlowLayout parent, final String title) {
        int margin = ScreenUtil.dipToPx(context, 8);
        TextView textView = new TextView(context);
        textView.setText(title);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_search_item));
        textView.setPadding(margin << 1, margin, margin << 1, margin);

        FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT
                , FlowLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = margin << 1;
        layoutParams.rightMargin = margin << 1;
        parent.addView(textView, layoutParams);

        if (onCityClickListener != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCityClickListener.onCityListener(title);
                }
            });
        }
    }

    public static class CityViewHolder{
        TextView letter;
        TextView name;
    }

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        this.onCityClickListener = onCityClickListener;
    }

    public interface OnCityClickListener {
        void onCityListener(String name);
    }
}
