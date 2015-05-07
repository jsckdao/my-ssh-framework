package org.yonixee.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yonixee on 15/5/7.
 */
public class Page<T> implements Serializable {

    /**
     * 结果集
     */
    private List<T> result;
    /**
     * 每一页的数据量
     */
    private int size = 10;
    /**
     * 当前页
     */
    private int current = 1;
    /**
     * 总数据量
     */
    private long total;
    /**
     * 排序字段名
     */
    private String sortField;
    /**
     * 是否顺序
     */
    private boolean asc = true;

    /**
     * @return 结果集数据
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * @param result 结果集数据
     */
    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * @return 每一页的数据量
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size 每一页的数据量
     */
    public void setSize(int size) {
        this.size = Math.max(1, size);
    }

    /**
     * @return 总数据量
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total 总数据量
     */
    public void setTotal(long total) {
        this.total = Math.max(0, total);
    }

    /**
     * @return 当前页
     */
    public int getCurrent() {
        return Math.min(current, getTotalPage());
    }

    /**
     * @param current 当前页
     */
    public void setCurrent(int current) {
        this.current = Math.max(1, current);
    }

    /**
     * @return 总页数
     */
    public int getTotalPage() {
        return (int) Math.ceil(((double) total) / size);
    }

    /**
     * @return 获取数据库查询偏移量
     */
    public int getOffset() {
        return (getCurrent() - 1) * getSize();
    }

    /**
     * 设置排序方式
     *
     * @param field 排序的列名
     * @param asc   是否正序
     */
    public void setSort(String field, boolean asc) {
        this.sortField = field;
        this.asc = asc;
    }

    /**
     * @return 返回排序的字段
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * @return 是否顺序排序
     */
    public boolean isSortAsc() {
        return asc;
    }

    /**
     * @return the asc
     */
    public boolean isAsc() {
        return asc;
    }

    /**
     * @param asc the asc to set
     */
    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    /**
     * @param sortField the sortField to set
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}

