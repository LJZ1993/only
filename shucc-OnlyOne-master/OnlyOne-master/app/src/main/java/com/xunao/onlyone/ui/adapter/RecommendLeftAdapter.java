package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.model.Category;
import com.xunao.onlyone.ui.fragment.SortFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 * SortFragment中的左侧推荐的Adapter
 */
public class RecommendLeftAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categoryList;
    //private String[] recommendData;
    //选中的item
    private static int selectItem;
    public RecommendLeftAdapter(Context context, List<Category> categoryList){
        this.context=context;
        this.categoryList=categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_recommend_left,null);
            holder.item_text = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        selectItem=position;
        if (SortFragment.selectItem== position){
         //改变背景
            convertView.setBackgroundResource(R.drawable.ic_item_sort);
        }else{
            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        }
        holder.item_text.setText( categoryList.get(position).getSortName());
        return convertView;
    }
    class ViewHolder{
        TextView item_text;
    }
}
