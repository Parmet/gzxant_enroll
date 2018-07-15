package com.gzxant.controller.enroll.enter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gzxant.controller.enroll.personnel.EnrollPersonnelController;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.service.ISysDictService;
import com.gzxant.service.personnel.IEnrollPersonnelService;
import com.gzxant.util.StringUtils;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gzxant.annotation.SLog;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.base.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 参赛者信息 前端控制器
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@Controller
@RequestMapping("/enter")
public class EnrollEnterController extends BaseController {
	@Autowired
	private IEnrollEnterService enrollEnterService;
	@Autowired
	private IEnrollPersonnelService enrollPersonnelService;
	@Autowired
	private ISysDictService dictService;
	@Autowired
	private EnrollPersonnelController enrollPersonnelController;
	private MultipartFile files;
	private String fileName;


	@ApiOperation(value = "进入参赛者信息列表界面", notes = "进入参赛者信息列表界面")
	@GetMapping(value = "")
	public String list(Model model) {
		model.addAttribute("enterType", dictService.getDictTree("ENTER_TYPE"));
		return "/enroll/enter/list";
	}
	/**
	 * 检查用户名是否存在
	 *
	 * @param numbers
	 * @return
	 */
	@GetMapping(value = "/check")
	@ResponseBody
	public Boolean check( @RequestParam("numbers") String numbers) {

		return enrollPersonnelService.checknumbers(numbers);
	}

	@ApiOperation(value = "查看上传的图片", notes = "查看上传的图片")
	@PostMapping(value = "/importexecldate")
	@ResponseBody
	public ReturnDTO getImageByPath(Model model,@RequestParam("path")String path) throws IOException {
		if (path == null) {
			return ReturnDTOUtil.paramError();
		}
		boolean isSuccess = false;
		try {
			isSuccess = enrollEnterService.batchImport(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isSuccess) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}


	@ApiOperation(value = "获取参赛者信息列表数据", notes = "获取参赛者信息列表数据:使用约定的DataTable")
	@PostMapping(value = "/list")
	@ResponseBody
	public DataTable<EnrollEnter> list(@RequestBody DataTable<EnrollEnter> dt) {
		return enrollEnterService.pageSearch(dt);
	}

	@ApiOperation(value = "进入参赛者信息编辑界面", notes = "进入参赛者信息编辑界面")
	@GetMapping(value = "/{action}/{id}")
	public String detail(@PathVariable("action") String action, @PathVariable("id") String id, Model model) {
		if (TextUtils.isEmpty(id)||TextUtils.isEmpty(action)){
			if (org.apache.commons.lang3.StringUtils.isBlank(id)) {
				return "redirect:/enter";
			}
		}
		EnrollEnter enrollEnter = enrollEnterService.selectById(id);
		model.addAttribute("enrollEnter", enrollEnter);
		model.addAttribute("action", action);
		return "/enroll/enter/detail";
	}


	@ApiOperation(value = "添加参赛者信息", notes = "添加参赛者信息")
	@PostMapping(value = "/insert")
	@ResponseBody
	public ReturnDTO create(EnrollEnter param) {
		if (param == null) {
			return ReturnDTOUtil.paramError();
		}
		enrollEnterService.insert(param);
		return ReturnDTOUtil.success();
	}

	@ApiOperation(value = "进入编辑参赛者信息", notes = "进入编辑参赛者信息")
	@GetMapping(value = "/insert")
	public String importDate(Model model) {
		model.addAttribute("action", "insert");
		model.addAttribute("enterType", dictService.getDictTree("ENTER_TYPE"));
		return "/enroll/enter/insert";
	}

	@ApiOperation(value = "进入编辑参赛者信息", notes = "进入编辑参赛者信息")
	@GetMapping(value = "/import")
	public String importExeccl(Model model) {
		model.addAttribute("action", "importexecldate");
//		model.addAttribute("enterType",dictService.getDictTree("ENTER_TYPE"));
		return "/enroll/enter/import";
	}

	@ApiOperation(value = "编辑参赛者信息", notes = "编辑参赛者信息")
	@PostMapping(value = "/update")
	@ResponseBody
	public ReturnDTO update(EnrollEnter param) {
		if (param == null) {
			return ReturnDTOUtil.paramError();
		}
		enrollEnterService.updateById(param);
		return ReturnDTOUtil.success();
	}

	@SLog("批量删除参赛者信息")
	@ApiOperation(value = "批量删除参赛者信息", notes = "批量删除参赛者信息")
	@PostMapping(value = "/delete")
	@ResponseBody
	public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
		if (ids==null){
				return ReturnDTOUtil.paramError();
		}
		boolean success = enrollEnterService.deleteBatchIds(ids);
		if (success) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}



}
