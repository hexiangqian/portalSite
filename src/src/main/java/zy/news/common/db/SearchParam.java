package zy.news.common.db;

/**
 * 筛选参数 若数据为范围数据，以|为分割符，前段数据为起始数据，后断数据为截止数据，例：2019-10-8|2019-10—12
 *
 * @author fanpei
 */
public class SearchParam {

    private byte rule;// 筛选规则
    protected String key;
    protected Object value;//可做list

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DbMatchRule getRule() {
        return DbMatchRule.getDbMatchRule(rule);
    }

    public void setRule(DbMatchRule rule) {
        this.rule = rule.getValue();
    }

    public SearchParam() {
    }

    public SearchParam(String key, Object value, DbMatchRule rule) {
        this.key = key;
        this.value = value;
        this.rule = rule.getValue();
    }

    public SearchParam(String key, DbMatchRule rule) {
        this.key = key;
        this.rule = rule.getValue();
    }
}
