package com.bjpowernode.crm.vo;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * lzx
 * 2020/3/18
 */
public class PaginationVo<T> {

    private List<T> dataList;
    private int total;

    public List<T> getDataList() {
        return dataList;
    }

    public PaginationVo<T> setDataList(List<T> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PaginationVo<T> setTotal(int total) {
        this.total = total;
        return this;
    }
}
