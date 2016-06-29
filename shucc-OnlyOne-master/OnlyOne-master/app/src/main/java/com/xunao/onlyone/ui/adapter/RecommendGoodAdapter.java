package com.xunao.onlyone.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.widget.HorizontalProgressView;
import com.xunao.onlyone.model.Good;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/6/16.
 * cc@cchao.org
 * 推荐商品
 */
public class RecommendGoodAdapter extends RecyclerView.Adapter<RecommendGoodAdapter.RecommendHolder> {

    private List<Good> data;

    public RecommendGoodAdapter(List<Good> goodList) {
        this.data = goodList;
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    @Override
    public RecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_good, null);
        return new RecommendHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecommendHolder holder, int position) {
        Good good = data.get(position);
        ImageLoader.getInstance().displayImage(good.getGoodUrl(), holder.imgGood);
        holder.textGoodName.setText(good.getGoodName());
        holder.progressView.setProgress(good.getProgress());
    }

    public class RecommendHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_good)
        ImageView imgGood;
        @BindView(R.id.text_good_name)
        TextView textGoodName;
        @BindView(R.id.progress_view)
        HorizontalProgressView progressView;

        public RecommendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
