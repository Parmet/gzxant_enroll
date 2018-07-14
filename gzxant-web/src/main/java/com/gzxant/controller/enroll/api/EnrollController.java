package com.gzxant.controller.enroll.api;

import com.gzxant.base.controller.BaseController;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import com.gzxant.service.personnel.IEnrollPersonnelService;
import com.gzxant.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 参赛者信息 前端控制器
 * </p>
 *
 * @author tecty
 * @since 2018-07-06
 */
@RestController
@RequestMapping("/api")
public class EnrollController extends BaseController {
	private static int RESULT_SUCCESS=1000;
	private static int PARARM_FAIL=1001;
	private static int NOT_RESULT_SUCCESS=1002;

	@Autowired
	private IEnrollPersonnelService enrollPersonnelService;
	@Autowired
	private IEnrollEnterService enrollEnterService;


	/**
	 * 登录接口
	 * @param model
	 * @param name
	 * @param password
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "/login", method = { RequestMethod.POST },produces = "application/json;charset=UTF-8" )
	public ReturnDTO login(Model model, @RequestParam("account")String name, @RequestParam("password")String password) throws IOException {
		if (StringUtils.isEmpty(name)) {
			return new ReturnDTO(PARARM_FAIL, "用户名不能为空");
		}
		if (StringUtils.isEmpty(password)) {
			return new ReturnDTO(PARARM_FAIL, "密码不能为空");
		}
		EnrollPersonnel enrollPersonnel = enrollPersonnelService.login(name, password);
		if (enrollPersonnel == null) {
			return new ReturnDTO(NOT_RESULT_SUCCESS, "用户名或密码错误");
		}

		//返回数据到前端
		EnrollPersonnel enrollPersonnel1Josn = new EnrollPersonnel();
		enrollPersonnel1Josn.setId(enrollPersonnel.getId());
		enrollPersonnel1Josn.setName(enrollPersonnel.getName());
		enrollPersonnel1Josn.setStyle(enrollPersonnel.getStyle());
		return new ReturnDTO(RESULT_SUCCESS, "登录成功", enrollPersonnel1Josn);
	}

	/**
	 * 参赛者管理信息
	 * @param model
	 * @param numders
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/enter/{numbers}", method = { RequestMethod.GET })
	public ReturnDTO findbyIdEnterdate(Model model, @PathVariable("numders")String numders) throws IOException {
		if(StringUtils.isEmpty(numders)){
			return new ReturnDTO(PARARM_FAIL, "参数不能为空");
		}

		EnrollEnter enrollEnter=enrollEnterService.findbyIdEnterdate(numders);
		if(enrollEnter==null){
			return new ReturnDTO(NOT_RESULT_SUCCESS, "没有查到数据");
		}
		return new ReturnDTO(RESULT_SUCCESS, "成功",enrollEnter);
	}
	/**
	 * 不全参赛者管理信息
	 * @param model
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/enter", method = { RequestMethod.GET } )
	public ReturnDTO findbyALlEnterdate(Model model) throws IOException {
		List<EnrollEnter> list = enrollEnterService.selectList(null);
		//返回数据到前端
		if (list==null) {
			return new ReturnDTO(NOT_RESULT_SUCCESS, "没有查到数据");
		}

		return new ReturnDTO(RESULT_SUCCESS, "成功",list);
	}


}
