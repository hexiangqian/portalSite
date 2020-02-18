package zy.news.common.db;

/**
 * 字段描述信息
 */
public class SqlFieldBook {

    private String table;//数据库表名


    public SqlFieldBook(String table) {
        this.table = table;
    }

    public String getKey(String fieldName) {
        return generateKey(fieldName, this.table);
    }

    public String getKeyWithoutTable(String fieldName) {
        return generateKey(fieldName, "");
    }

    /**
     * 产生key
     *
     * @param tableNam
     * @return
     */
    public static String generateKey(String fieldName, String tableNam) {
        if (tableNam == "") //-1时不考虑 默认为全部
        {
            return new StringBuilder(fieldName).toString();
        } else {
            return new StringBuilder(fieldName).append(tableNam).toString();
        }
    }
}
