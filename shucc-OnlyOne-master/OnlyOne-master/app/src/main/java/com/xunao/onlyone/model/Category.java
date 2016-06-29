package com.xunao.onlyone.model;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public class Category {
    private String sortName;
    private boolean isSelect;
    private List<Team> teamList;

    public Category(List<Team> teamList, String sortName) {
        this.teamList = teamList;
        this.sortName = sortName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
}
