package com.xunao.onlyone.model;

/**
 * Created by Administrator on 2016/6/29.
 */
public class Team {
    private  String name;
    private String imagePath;

    public Team(String name, String imgPath) {
        this.name=name;
        this.imagePath=imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
