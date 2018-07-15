package com.gzxant.controller.article;

import com.gzxant.annotation.SLog;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.common.service.dict.ISysDictService;
import com.gzxant.entity.enroll.article.EnrollArticle;
import com.gzxant.service.article.IEnrollArticleService;
import com.gzxant.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *     文章信息 前台控制器
 * </p>
 *
 * @author: Fatal
 * @date: 2018/7/12 0012 12:50
 */
@Controller
@RequestMapping("/article")
public class EnrollArticleController {

    @Autowired
    private ISysDictService dictService;

    @Autowired
    private IEnrollArticleService enrollArticleService;

    @ApiOperation(value = "进入文章信息列表界面", notes = "进入文章信息列表界面")
    @GetMapping(value = "")
    public String list(Model model) {
        return "/enroll/article/list";
    }


    @ApiOperation(value = "获取文章信息列表数据", notes = "获取文章信息列表数据:使用约定的DataTable")
    @PostMapping(value = "/list")
    @ResponseBody
    public DataTable<EnrollArticle> list(@RequestBody DataTable<EnrollArticle> dt) {
        DataTable<EnrollArticle> dataTable = enrollArticleService.pageSearch(dt);
        return dataTable;
    }

    @ApiOperation(value = "进入文章信息编辑界面", notes = "进入文章信息编辑界面")
    @GetMapping(value = "/{action}/{id}")
    public String detail(@PathVariable("action") String action, @PathVariable("id") String id, Model model) {
        EnrollArticle enrollArticle = enrollArticleService.selectById(id);
        model.addAttribute("enrollArticle", enrollArticle);
        model.addAttribute("action", action);
        return "/enroll/article/detail";
    }

    @ApiOperation(value = "添加文章信息", notes = "添加文章信息")
    @PostMapping(value = "/insert")
    @ResponseBody
    public ReturnDTO create(EnrollArticle enrollArticle) {
        Integer isRelease = enrollArticle.getIsRelease();
        if (isRelease != null && isRelease == 1) {
            enrollArticle.setReleaseDate(new Timestamp(System.currentTimeMillis()));
        }
        enrollArticleService.insert(enrollArticle);
        return ReturnDTOUtil.success();
    }

    @ApiOperation(value = "进入编辑文章信息", notes = "进入编辑文章信息")
    @GetMapping(value = "/insert")
    public String importDate(Model model) {
        model.addAttribute("action", "insert");
        return "/enroll/article/insert";
    }

    @ApiOperation(value = "编辑参赛者信息", notes = "编辑参赛者信息")
    @PostMapping(value = "/update")
    @ResponseBody
    public ReturnDTO update(EnrollArticle param) {
        enrollArticleService.updateById(param);
        return ReturnDTOUtil.success();
    }


    @SLog("批量删除文章信息")
    @ApiOperation(value = "批量删除文章信息", notes = "批量删除文章信息")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
        boolean success = enrollArticleService.deleteBatchIds(ids);
        if (success) {
            return ReturnDTOUtil.success();
        }
        return ReturnDTOUtil.fail();
    }

    /**
     * form表单提交 Date类型数据绑定
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//第二个参数是控制是否支持传入的值是空，这个值很关键，如果指定为false，那么如果前台没有传值的话就会报错
    }

}
