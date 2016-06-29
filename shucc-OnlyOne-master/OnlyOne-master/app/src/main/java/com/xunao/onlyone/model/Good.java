package com.xunao.onlyone.model;

/**
 * Created by chenchao on 16/6/1.
 * cc@cchao.org
 * 首页商品列表数据
 */
public class Good {

    //商品图片
    private String goodUrl;
    //商品名
    private String goodName;
    //总数
    private int goodTotal;
    //剩余数
    private int goodOverage;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodOverage() {
        return goodOverage;
    }

    public void setGoodOverage(int goodOverage) {
        this.goodOverage = goodOverage;
    }

    public int getGoodTotal() {
        return goodTotal;
    }

    public void setGoodTotal(int goodTotal) {
        this.goodTotal = goodTotal;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    /**
     * 获取进度
     * @return
     */
    public int getProgress() {
        if (goodTotal == 0)
            return 0;
        if (goodOverage == 0)
            return 100;
        return (goodTotal - goodOverage) * 100 / goodTotal ;
    }
}
