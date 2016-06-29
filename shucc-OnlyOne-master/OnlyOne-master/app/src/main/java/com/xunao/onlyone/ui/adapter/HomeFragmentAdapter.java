package com.xunao.onlyone.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xunao.onlyone.R;
import com.xunao.onlyone.ui.base.BaseActivity;
import com.xunao.onlyone.ui.widget.HorizontalProgressView;
import com.xunao.onlyone.util.ScreenUtil;
import com.xunao.onlyone.model.Good;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/6/1.
 * cc@cchao.org
 */
public class HomeFragmentAdapter extends LoadMoreAdapter {

    private BaseActivity baseActivity;
    private List<Good> data;

    public HomeFragmentAdapter(BaseActivity baseActivity, RecyclerView recyclerView, OnLoadMoreListener onloadMoreListener, List<Good> data) {
        super(recyclerView, onloadMoreListener);
        this.baseActivity = baseActivity;
        this.data = data;
    }

    @Override
    int getDataSize() {
        if (data == null)
            return 0;
        return data.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LOAD_MORE_ITEM) {
            return super.onCreateViewHolder(parent, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_fragment_good, parent, false);

            //设置商品图片比例
            ImageView imgGood = (ImageView) view.findViewById(R.id.img_good);
            ViewGroup.LayoutParams params = imgGood.getLayoutParams();
            params.height = (ScreenUtil.width(baseActivity).px >> 1) - ScreenUtil.dipToPx(baseActivity, 16);
            imgGood.setLayoutParams(params);

            return new NormalViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {

            Good good = data.get(position);

            ImageLoader.getInstance()
                    .displayImage(good.getGoodUrl(), ((NormalViewHolder) holder).imgGood);
            ((NormalViewHolder) holder).textGoodName.setText(good.getGoodName());
            ((NormalViewHolder) holder).hpGoodProgress.setProgress(good.getProgress());
            ((NormalViewHolder) holder).textGoodProgress.setText(good.getProgress() + "%");
            ((NormalViewHolder) holder).textGoodOverage.setText(String.valueOf(good.getGoodOverage()));

            if (onItemClickListener != null) {
                ((NormalViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(v, pos);
                    }
                });
            }
        }
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_good) ImageView imgGood;
        @BindView(R.id.text_good) TextView textGoodName;
        @BindView(R.id.progress_view) HorizontalProgressView hpGoodProgress;
        @BindView(R.id.text_progress) TextView textGoodProgress;
        @BindView(R.id.text_overage) TextView textGoodOverage;
        @BindView(R.id.img_cart) ImageView imgCart;

        public NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
