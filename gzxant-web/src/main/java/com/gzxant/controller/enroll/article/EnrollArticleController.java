package com.gzxant.controller.enroll.article;

import com.alibaba.cloudapi.sdk.core.exception.SdkClientException;
import com.gzxant.annotation.SLog;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.entity.enroll.article.EnrollArticle;
import com.gzxant.entity.enroll.article.dto.EnrollArticleDTO;
import com.gzxant.enums.HttpCodeEnum;
import com.gzxant.service.article.IEnrollArticleService;
import com.gzxant.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private IEnrollArticleService enrollArticleService;

    @ApiOperation(value = "手机端文章信息列表界面", notes = "手机端文章信息列表界面")
    @GetMapping(value = "/mobile/list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnDTO list() {
        List<EnrollArticleDTO> enrollArticleDTOs = enrollArticleService.selectEnrollAricleList();
        if (enrollArticleDTOs != null && enrollArticleDTOs.size() != 0) {
            return ReturnDTOUtil.success(enrollArticleDTOs);
        } else {
            return ReturnDTOUtil.fail();
        }
    }

    @ApiOperation(value = "手机端文章信息列表界面", notes = "手机端文章信息列表界面")
    @GetMapping(value = "/mobile/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ReturnDTO detail(@PathVariable("id") Long id) {
        if (id == null) {
            throw new SdkClientException(HttpCodeEnum.INVALID_REQUEST.getMessage());
        }
        EnrollArticleDTO enrollArticleDTO = enrollArticleService.selectById(id);
        if (enrollArticleDTO != null ) {
            return ReturnDTOUtil.success(enrollArticleDTO);
        } else {
            return ReturnDTOUtil.fail();
        }
    }

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
    public ReturnDTO create(@Valid EnrollArticle enrollArticle, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                throw new SdkClientException(HttpCodeEnum.INVALID_REQUEST.getMessage());
            }
            return null;
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
    public ReturnDTO update(@Valid EnrollArticle enrollArticle, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(result.getFieldError().getDefaultMessage());
                throw new SdkClientException(HttpCodeEnum.INVALID_REQUEST.getMessage());
            }
            return null;
        }
        enrollArticleService.updateById(enrollArticle);
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
