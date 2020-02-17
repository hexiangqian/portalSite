package zy.news.common.db;

/**
 * 搜索元素 fieldName":"id","value":"1","oper":">"
 *
 * @author fanpei
 */
public class SearchElement {
    private String fieldName;
    private Object value;
    private String oper;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public SearchElement() {
    }

    /**
     * @param operV     操作符号
     * @param fieldName 数据库字段名
     * @param value     值(需要实现toString)
     */
    public SearchElement(IDbOperator operV, String fieldName, Object value) {
        this.oper = operV.getValue();
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * 转变成sql语句
     *
     * @return
     */
    public String toSqlString() {
        return new StringBuilder(fieldName).append(" ").append(oper).append(" ").append(value).toString();
    }
    public void setOper(IDbOperator oper) {
        this.oper = oper.getValue();
    }
}
