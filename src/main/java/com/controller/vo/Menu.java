package com.controller.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Integer id;

    private String name;

    private String url;

    private List<Menu> children = new ArrayList<Menu>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public void addChild(Menu menu) {
        this.children.add(menu);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
