package zy.news.common.db;

/**
 * 数据库操作符[支持mysql mysql mpp]
 *
 * @author fanpei
 */
public enum DbSqlOperator implements IDbOperator {
    EQUAL("="), //
    MORETHAN(">"), //
    LESSTHAN("<"), //
    MORETHANANDEQUAL(">="), //
    LESSTHANANDEQUAL("<="), //
    LIKE("like"), //
    NOTLIKE("not like"), //
    IN("in"), //
    AND("and"), //
    OR("or"),//
    NOTEQUAL("<>"),
    BETWEEN("between");

    private String value;

    @Override
    public String getValue() {
        return value;
    }

    private DbSqlOperator(String v) {
        this.value = v;
    }

    public DbMatchRule oper2MatchRule()
    {
        return oper2Rule(value);
    }
    public static DbMatchRule oper2Rule(String value) {
        DbMatchRule rule = DbMatchRule.EqualTo;
        switch (value) {
            case "=":
                rule = DbMatchRule.EqualTo;
                break;
            case ">":
                rule = DbMatchRule.GreaterThan;
                break;
            case ">=":
                rule = DbMatchRule.GreaterThanOrEqualTo;
                break;
            case "<":
                rule = DbMatchRule.LessThan;
                break;
            case "<=":
                rule = DbMatchRule.LessThanOrEqualTo;
                break;
            case "like":
                rule = DbMatchRule.Like;
                break;
            case "not like":
                rule = DbMatchRule.NotLike;
                break;
            case "in":
                rule = DbMatchRule.In;
                break;
            case "<>":
                rule = DbMatchRule.NotEqualTo;
                break;
            case "between":
                rule = DbMatchRule.Between;
                break;
            default:break;

        }
        return rule;
    }
}
