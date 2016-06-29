package com.xunao.onlyone.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xunao.onlyone.R;
import com.xunao.onlyone.model.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenchao on 16/6/20.
 * cc@cchao.org
 */
public class CitySearchAdapter extends RecyclerView.Adapter<CitySearchAdapter.CitySearchHolder> {

    List<City> cityList;

    private LoadMoreAdapter.OnItemClickListener onItemClickListener;

    public CitySearchAdapter(List<City> cityList) {
        this.cityList = cityList;
    }

    public void setOnItemClickListener(LoadMoreAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return cityList == null ? 0 : cityList.size();
    }

    @Override
    public CitySearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_search, null);
        return new CitySearchHolder(view);
    }

    @Override
    public void onBindViewHolder(final CitySearchHolder holder, int position) {
        holder.textCityName.setText(cityList.get(position).getName());
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(v, pos);
                }
            });
        }
    }

    public class CitySearchHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_city_name)
        TextView textCityName;

        public CitySearchHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
