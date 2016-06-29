package com.xunao.onlyone.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by chenchao on 16/5/31.
 * cc@cchao.org
 * 图片循环Adapter
 */
public class ImageLoopAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private String TAG = "AdvertisingAdapter";

    private Context context;
    private ViewPager viewPager;
    private List<String> imgUrlList;
    private OnPageSelectd onPageSelectd;
    private int imgSize;

    public ImageLoopAdapter(ViewPager viewPager, List<String> imgUrlList) {
        this.viewPager = viewPager;
        this.context = viewPager.getContext();
        this.imgUrlList = imgUrlList;
        imgSize = imgUrlList.size();
        viewPager.setOnPageChangeListener(this);
    }

    public int getRealCurrentItem(int position) {
        return imgSize * 1000 + position;
    }

    public void setOnPageSelectd(OnPageSelectd onPageSelectd) {
        this.onPageSelectd = onPageSelectd;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int index = position % imgUrlList.size();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(imageView);
        ImageLoader.getInstance().displayImage(imgUrlList.get(index), imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (onPageSelectd != null) {
            onPageSelectd.onPageSelected(position % imgSize);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnPageSelectd {
        void onPageSelected(int position);
    }
}
