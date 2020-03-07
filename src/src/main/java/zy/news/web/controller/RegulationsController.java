package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.web.bean.Regulations;
import zy.news.web.service.IRegulations;
import zy.news.web.zsys.bean.*;

import javax.servlet.http.HttpSession;

/**
 * @author maoko
 * @date 2020/3/7 14:30
 */
@RestController
@RequestMapping("/regulation")
@ExcuteControllerDsrc("质量专栏")
public class RegulationsController {

    private final IRegulations regulationsService;

    @Autowired
    public RegulationsController(IRegulations regulationsService) {
        this.regulationsService = regulationsService;
    }

    @GetMapping("existTitle")
    @ExcuteInterfaceDsrc("标题是否重复")
    @ExcutePermission
    public boolean existTitle(@RequestParam String title) throws Exception {
        Regulations record = new Regulations();
        record.setTitle(title);
        return regulationsService.exist(record);
    }

    @PostMapping("addRegulations")
    @ExcuteInterfaceDsrc("添加")
    @ExcutePermission
    public void addRegulations(HttpSession session, @RequestBody Regulations record) throws Exception {
        regulationsService.add(session, record);
    }

    @PostMapping("updateRegulations")
    @ExcuteInterfaceDsrc("更新")
    @ExcutePermission
    public void updateRegulations(HttpSession session, @RequestBody Regulations record) throws Exception {
        regulationsService.update(session, record);
    }

    @PostMapping("deleteRegulations")
    @ExcuteInterfaceDsrc("删除")
    @ExcutePermission
    public void deleteRegulations(HttpSession session, @RequestBody Regulations record) throws Exception {
        regulationsService.delete(session, record);
    }


    @PostMapping("getRegulations")
    @ExcuteInterfaceDsrc("获取质量文章列表")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public PageValuesResult<Regulations> getRegulationss(@RequestBody Page page) throws Exception {
        return regulationsService.getRegulations(page);
    }

    @GetMapping("getRegulationsDetail")
    @ExcuteInterfaceDsrc("获取详情")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public Regulations getRegulationsDetail(@RequestParam Long articleid) throws Exception {
        return regulationsService.getRecordDetail(articleid);
    }
}
