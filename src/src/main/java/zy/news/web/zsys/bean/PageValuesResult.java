package zy.news.web.zsys.bean;

import java.util.List;

/**
 * 分页列表结果
 *
 * @author maoko
 * @date 2020/2/18 23:29
 */
public class PageValuesResult<T> {
    private Page page;
    private List<T> value;
    private Object ext;// 额外


    public PageValuesResult() {
    }

    public PageValuesResult(Page page) {
        this.page = page.copy();
    }

    public PageValuesResult(List<T> value, Page page) {
        this.value = value;
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public List<T> getValue() {
        return value;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setValue(List<T> value) {
        this.value = value;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    /**
     * values是否为空
     *
     * @return
     */
    public boolean listValueEmpty() {
        return value == null || value.isEmpty();
    }
}
