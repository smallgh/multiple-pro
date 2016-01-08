package com.gaohan.result;

import java.util.List;

/**
 */
public class DataResult<T> {
    private List<T> data;
    private int count;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
