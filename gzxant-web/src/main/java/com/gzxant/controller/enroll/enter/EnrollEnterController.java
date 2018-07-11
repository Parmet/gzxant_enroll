package com.gzxant.controller.enroll.enter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gzxant.controller.enroll.personnel.EnrollPersonnelController;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.service.ISysDictService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.lang3.StringUtils;
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


	@ApiOperation(value = "查看上传的图片", notes = "查看上传的图片")
	@PostMapping(value = "/importexecldate")
	@ResponseBody
	public ReturnDTO getImageByPath(Model model,@RequestParam("path")String path) throws IOException {
		boolean isSuccess = false;
		try {
			isSuccess = batchImport(path);
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
		EnrollEnter enrollEnter = enrollEnterService.selectById(id);
		model.addAttribute("enrollEnter", enrollEnter);
		model.addAttribute("action", action);
		return "/enroll/enter/detail";
	}


	@ApiOperation(value = "添加参赛者信息", notes = "添加参赛者信息")
	@PostMapping(value = "/insert")
	@ResponseBody
	public ReturnDTO create(EnrollEnter param) {
		EnrollPersonnel enrollPersonnel = new EnrollPersonnel();
		enrollPersonnel.setPassword("123456");
		enrollPersonnel.setIdCard("440823198908216212");
		enrollPersonnel.setName(param.getName());
		enrollPersonnel.setPlace(param.getPlace());
		enrollPersonnel.setPhone("18665053437");
		enrollPersonnel.setRemark("这是后台添加用户");
		enrollPersonnel.setStyle("爵士");
		enrollPersonnelController.create(enrollPersonnel);
		Long id = enrollPersonnel.getId();
		param.setPersonnelId(enrollPersonnel.getId());
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
		enrollEnterService.updateById(param);
		return ReturnDTOUtil.success();
	}

	@SLog("批量删除参赛者信息")
	@ApiOperation(value = "批量删除参赛者信息", notes = "批量删除参赛者信息")
	@PostMapping(value = "/delete")
	@ResponseBody
	public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
		boolean success = enrollEnterService.deleteBatchIds(ids);
		if (success) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}


	public boolean batchImport(String fileName) throws Exception {
		File file = new File(fileName);
		InputStream targetStream = new FileInputStream(file);
		List<EnrollEnter> userList = new ArrayList<EnrollEnter>();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new Exception("上传文件格式不正确");
		}
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
			//InputStream is = targetStream.getInputStream();
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(targetStream);
			} else {
				wb = new XSSFWorkbook(targetStream);
			}
			Sheet sheet = wb.getSheetAt(0);


			EnrollEnter enrollEnter;
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				if (row == null) {
					continue;
				}
				enrollEnter = new EnrollEnter();
				if (row.getCell(0).getCellType() != 1) {
					throw new Exception("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
				}
				//获取参赛者id
				String id = row.getCell(0).getStringCellValue();

				if (id == null || id.isEmpty()) {
					throw new Exception("导入失败(第" + (r + 1) + "行,姓名未填写)");
				}

				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				//姓名
				String name = row.getCell(1).getStringCellValue();
				if (name == null || name.isEmpty()) {
					throw new Exception("导入失败(第" + (r + 1) + "行,电话未填写)");
				}
				//获取地点
				String palce = row.getCell(4).getStringCellValue();
				if (palce == null) {
					throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
				}

				//获取状态
				String state = row.getCell(8).getStringCellValue();
				if (state == null) {
					throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
				}

				//获取分数
				row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
				String fraction = row.getCell(9).getStringCellValue()+"";
				if (fraction == null) {
					throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
				}
				//获取歌曲
				String song = row.getCell(10).getStringCellValue();
				if (song == null) {
					throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
				}
				//获取类型
				String type = row.getCell(11).getStringCellValue();
				if (type == null) {
					throw new Exception("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
				}

				enrollEnter.setName(name);
				enrollEnter.setPersonnelId(Long.parseLong(id));
				enrollEnter.setFraction(fraction);
				enrollEnter.setPlace(palce);
				enrollEnter.setSong(song);
				enrollEnter.setType(type);
				if (state.equals("成功")) {
					enrollEnter.setState("Y");
				} else {
					enrollEnter.setState("N");
				}

				userList.add(enrollEnter);
			}
			for (EnrollEnter userResord : userList) {
				enrollEnterService.insert(userResord);

			}
			return true;
		}

}
