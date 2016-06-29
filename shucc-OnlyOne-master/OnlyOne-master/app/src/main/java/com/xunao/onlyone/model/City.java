package com.xunao.onlyone.model;

import org.litepal.crud.DataSupport;

/**
 * Created by chenchao on 16/6/20.
 * cc@cchao.org
 */
public class City extends DataSupport {

    private String label;

    private String name;

    private String pinyin;

    private String zip;

    public City(String name, String pinyin) {
        this.name = name;
        this.pinyin = pinyin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
