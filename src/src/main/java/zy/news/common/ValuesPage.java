package zy.news.common;


import java.util.List;

/**
 * 具有列表的分页信息
 *
 * @author fanpei
 */
public class ValuesPage {
    private Object ext;// 额外
    private Page page;
    private List value;


    public ValuesPage() {
    }

    public ValuesPage(Page page) {
        this.page = page.copy();
    }

    public ValuesPage(List value, Page page) {
        this.value = value;
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public List getValue() {
        return value;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setValue(List value) {
        this.value = value;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    public boolean listValueEmpty() {
        return value == null || value.isEmpty();
    }
}
