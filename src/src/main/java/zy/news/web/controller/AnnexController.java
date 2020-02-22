package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zy.news.web.bean.ArticlAnnex;
import zy.news.web.service.IAnnex;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
import zy.news.web.zsys.bean.ExcutePermission;

import java.util.List;

/**
 * 附件管理
 *
 * @author maoko
 * @date 2020/2/22 22:49
 */
@RestController
@RequestMapping("/annex")
@ExcuteControllerDsrc("附件管理")
@Deprecated
public class AnnexController {

    @Autowired
    private IAnnex annexService;

    @PostMapping("getAnnexs")
    @ExcuteInterfaceDsrc("获取指定文章附件列表")
    @ExcutePermission
    public List<ArticlAnnex> getAnnexs(Long articleid) throws Exception {
        return annexService.getAnnexs(articleid);
    }


}
