package zy.news.common;

import com.google.gson.annotations.SerializedName;
import zy.news.web.zsys.gson.MyExpose;

/**
 * 分页信息 默认分页大小为20 默认为第一页
 *
 * @author fanpei
 */
public class Page {

    @MyExpose(serialize = false)
    protected int number; // 当前页数0开始
    protected int current; // 当前页数1开始
    @SerializedName("pageSize")
    protected int size; // 每页条数
    @SerializedName("total")
    private long totalElements; // 总条数
    private long totalPages; // 总页数

    public long getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 分页大小
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPageSize(int size) {
        setSize(size);
    }

    /**
     * 页码
     *
     * @return
     */
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    /**
     * 总个数
     *
     * @return
     */
    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        if (totalElements != 0) {
            if (current > 0 && size > 0) {
                this.totalPages = totalElements / size + 1;
            }
        }
    }

    /**
     * 总页码
     *
     * @return
     */
    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotal(long total) {
        setTotalElements(total);
    }


    public Page() {
        this.totalPages = 1;
    }

    public Page(int current, int pageSize) {
        this.number = current;
        this.current = current;
        this.size = pageSize;
    }

    public Page(int current, int pageSize, int totalPages) {
        this.current = current;
        this.number = current;
        this.size = pageSize;
        this.totalPages = totalPages;
    }

    public Page copy() {
        Page page = new Page();
        page.current = current;
        page.number = page.number;
        page.size = size;
        page.totalPages = totalPages;
        return page;
    }

    public void init() {
        this.current = number + 1;
    }

    public int getStartIndex() {
        return (current - 1) * size + 1;
    }

    public int getEndIndex() {
        return current * size;
    }
}
