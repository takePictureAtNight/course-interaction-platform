package com.peking.courseresourse.entity;

import lombok.Data;

import java.util.List;
@Data
public class Tree {

    private Integer id;

    private Integer pId;

    private String title;

    private List<Tree> children;

    private Boolean spread;

    public void setTitle(String name) {
    }

    public void setSpread(boolean b) {
    }

    public int getPId() {
        return pId;
    }

//    public void setChildren(ArrayList<Tree> trees) {
//
//    }
//
//    public Collection<Tree> getChildren() {
//    }

    public int getId() {
        return id;
    }
}
