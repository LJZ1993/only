package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.xunao.onlyone.R;
import com.xunao.onlyone.model.Category;
import com.xunao.onlyone.model.Team;
import com.xunao.onlyone.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 * 推荐界面右边的recycerView的adapter,是有标题的，所以需要Header
 */
public class RecommendRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Category> categoryList;
    private List<Team> teamList = new ArrayList<>();

    public RecommendRightAdapter(Context context, List<Category> categoryList) {
        this.mContext = context;
        setCategoryList(categoryList);

    }

    //每个category对应的team的数据
    private void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        for (int i = 0; i < categoryList.size(); i++) {

            teamList.addAll(categoryList.get(i).getTeamList());
        }
        notifyDataSetChanged();
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }


    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_right, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentViewHolder viewHolder = (ContentViewHolder) holder;
        viewHolder.text_good.setText(teamList.get(position).getName());
       // viewHolder.img_good.setImageURI(null);
        ImageLoader.getInstance()
                .displayImage(teamList.get(position).getImagePath(),viewHolder.img_good);

    }

    @Override
    public long getHeaderId(int position) {
        return getCategoryType(position);
    }

    //获取item对应的Category
    public  int getCategoryType(int position) {
        int sort = -1;
        int sum = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            if (position >= sum) {
                sort++;
            } else {
                return sort;
            }
            sum += categoryList.get(i).getTeamList().size();
        }
        return sort;
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        //加载item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recmmend_right_header, parent, false);
        return new HeaderViewHolder(view);

    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;
        tv.setText(categoryList.get(getCategoryType(position)).getSortName());
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemClickListener(BaseActivity baseActivity) {
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView text_good;
        ImageView img_good;

        public ContentViewHolder(View itemView) {
            super(itemView);
            img_good = (ImageView) itemView.findViewById(R.id.img_good);
            text_good = (TextView) itemView.findViewById(R.id.text_good);

        }

    }
}


