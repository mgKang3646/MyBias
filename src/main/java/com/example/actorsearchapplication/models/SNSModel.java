package com.example.actorsearchapplication.models;

import java.io.Serializable;

public class SNSModel implements Serializable {

    private int sort;
    private String id;

    public SNSModel(int sort, String id) {
        this.sort = sort;
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
