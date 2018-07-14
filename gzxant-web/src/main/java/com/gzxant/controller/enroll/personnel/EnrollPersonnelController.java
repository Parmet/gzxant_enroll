package com.gzxant.controller.enroll.personnel;

import java.util.ArrayList;
import java.util.List;

import com.gzxant.entity.SysUser;
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
import com.gzxant.service.personnel.IEnrollPersonnelService;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

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
@RequestMapping("/personnel")
public class EnrollPersonnelController extends BaseController {
	@Autowired
	private IEnrollPersonnelService enrollPersonnelService;

	@ApiOperation(value = "进入参赛者信息列表界面", notes = "进入参赛者信息列表界面")
	@GetMapping(value = "")
	public String list(Model model) {
		return "/enroll/personnel/list";
	}

	@ApiOperation(value = "进入参赛者信息编辑界面", notes = "进入参赛者信息编辑界面")
	@GetMapping(value = "/{action}/{id}")
	public String detail(@PathVariable("action") String action,@PathVariable("id") String id, Model model) {
		EnrollPersonnel enrollPersonnel = enrollPersonnelService.selectById(id);
		model.addAttribute("enrollPersonnel", enrollPersonnel);
		model.addAttribute("action", action);
		return "/enroll/personnel/detail";
	}

	/**
	 * 导出 exccel
	 * * @param dt
	 *
	 * @throws Exception
	 */
	@GetMapping(value = "/excel")
	@ResponseBody
	public void exportCollectCustomerList(HttpServletResponse response) throws Exception {
	List<EnrollPersonnel> enrollPersonnelss = enrollPersonnelService.selectList(null);

	//导出数据
	String excelTitle = "用户列表";
	String[] headerTitle = new String[]{"id", "参赛者姓名", "手机号", "身份证", "报名地点", "工作单位", "职业", "歌唱风格"};
	List<String[]> arrayList = new ArrayList<>();
        arrayList.add(headerTitle); //列头
        if (null != enrollPersonnelss && enrollPersonnelss.size() > 0) {
		for (EnrollPersonnel enrollPersonnel : enrollPersonnelss) {
			arrayList.add(
					new String[]{
							enrollPersonnel.getId().toString(),
							enrollPersonnel.getName(),
							enrollPersonnel.getPhone(),
							enrollPersonnel.getIdCard(),
							enrollPersonnel.getPlace(),
							enrollPersonnel.getCompany(),
							enrollPersonnel.getProfession(),
							enrollPersonnel.getStyle()

					});
		}
	}
	ExceptInfo(response, excelTitle, arrayList);
}
	@ApiOperation(value = "获取参赛者信息列表数据", notes = "获取参赛者信息列表数据:使用约定的DataTable")
	@PostMapping(value = "/list")
	@ResponseBody
	public DataTable<EnrollPersonnel> list(@RequestBody DataTable<EnrollPersonnel> dt) {
		return enrollPersonnelService.pageSearch(dt);
	}
	/**
	 * 检查用户名是否存在
	 *
	 * @param name
	 * @return
	 */
	@GetMapping(value = "check/{id}")
	@ResponseBody
	public Boolean check(@PathVariable("id") Long id, @RequestParam("name") String name) {
		return enrollPersonnelService.checkLoginName(name, id);
	}
	@ApiOperation(value = "添加参赛者信息", notes = "添加参赛者信息")
	@PostMapping(value = "/insert")
	@ResponseBody
	public ReturnDTO create(EnrollPersonnel param) {
		enrollPersonnelService.insert(param);
		return ReturnDTOUtil.success();
	}

	@ApiOperation(value = "编辑参赛者信息", notes = "编辑参赛者信息")
	@PostMapping(value = "/update")
	@ResponseBody
	public ReturnDTO update(EnrollPersonnel param) {
		enrollPersonnelService.updateById(param);
		return ReturnDTOUtil.success();
	}

	@ApiOperation(value = "进入编辑参赛者信息", notes = "进入编辑参赛者信息")
	@GetMapping(value = "/insert")
	public String importDate(Model model) {
		model.addAttribute("action", "insert");
		EnrollPersonnel enrollPersonnel = new EnrollPersonnel();
		enrollPersonnel.setId(0L);
		enrollPersonnel.getName();
		enrollPersonnel.setPassword("");
		model.addAttribute("enrollPersonnel", enrollPersonnel);
		return "/enroll/personnel/insert";
	}

	@SLog("批量删除参赛者信息")
	@ApiOperation(value = "批量删除参赛者信息", notes = "批量删除参赛者信息")
	@PostMapping(value = "/delete")
	@ResponseBody
	public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
		boolean success = enrollPersonnelService.deleteBatchIds(ids);
		if (success) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}
}
