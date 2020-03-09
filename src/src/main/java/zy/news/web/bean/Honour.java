package zy.news.web.bean;

import lombok.Data;
import maoko.common.StringUtil;
import zy.news.web.zsys.bean.IValidate;

import java.io.Serializable;
import java.util.Date;

@Data
public class Honour extends ContentBase implements IValidate {
    private Long id;
    private Long imageid;//可为空
    private String pepole;
    private String honortitle;
    private String title;
    private String author;
    private Date publishdate;
    private Long pageview;

    //append
    private String imageUrl;

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(pepole)) {
            throw new Exception("pepole字段为空！");
        }
        if (StringUtil.isStrNullOrWhiteSpace(honortitle)) {
            throw new Exception("honortitle字段为空！");
        }
        if (honortitle.length() > 200) {
            throw new Exception("honortitle文字个数过长，限定200个汉字和标点！");
        }
        if (StringUtil.isStrNullOrWhiteSpace(title)) {
            throw new Exception("title字段为空！");
        }
        super.validate();
    }
}