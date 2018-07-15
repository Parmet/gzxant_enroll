package com.gzxant.common.controller.message;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.annotation.SLog;
import com.gzxant.base.controller.BaseController;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.base.vo.DataTable;
import com.gzxant.common.entity.message.SendMessage;
import com.gzxant.common.entity.message.SysMessage;
import com.gzxant.common.service.message.IMessageService;
import com.gzxant.common.service.message.ISysMessageService;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 短信表 前端控制器
 * </p>
 *
 * @author ycxiao
 * @since 2018-07-15
 */
@Controller
@RequestMapping("/sys/message")
public class SysMessageController extends BaseController {
	@Autowired
	private ISysMessageService sysMessageService;

	@Autowired
	private IMessageService messageService;

	@GetMapping(value = "/send/{phone}")
	@ResponseBody
	public ReturnDTO send(@PathVariable("phone") String phone) {
		if (StringUtils.isBlank(phone)
				|| !StringUtils.isMobile(phone)) {
			return ReturnDTOUtil.custom(1001, "参数为空");
		}

		SendMessage msg = new SendMessage();
		List<String> phones = new ArrayList<>();
		phones.add(phone);
		msg.setPhone(phones);

		// 验证码
		int code = (int)((Math.random()*9+1)*100000);
		System.out.println("=================================================");
		System.out.println(code);
		System.out.println("=================================================");
		// 保存短信信息
		SysMessage sysMessage = new SysMessage();
		sysMessage.setCode(code);
		sysMessage.setPhone(msg.getPhone().get(0));
		sysMessage.setState("UNUSED");
		sysMessage.setType("MESSAGE_CODE");
		sysMessage.setCreateId(Long.parseLong(phone));
		sysMessage.setUpdateId(Long.parseLong(phone));
		sysMessageService.insert(sysMessage);

		// 发送短信
		Map<String, Object> params = new HashMap<>();
		params.put("code", String.valueOf(code));
		msg.setParam(params);
		msg.setTemplateKey("MESSAGE_CODE");
		if (messageService.send(msg)) {
			return ReturnDTOUtil.success();
		}

		return ReturnDTOUtil.custom(1002, "发送短信失败，请稍候重试");
	}

	@GetMapping(value = "/check/{phone}/{code}")
	@ResponseBody
	public ReturnDTO check(@PathVariable("phone") String phone,
						   @PathVariable("code") String code) {
		if (StringUtils.isBlank(phone)
				|| !StringUtils.isMobile(phone)
				|| StringUtils.isBlank(code)) {
			return ReturnDTOUtil.custom(1001, "参数为空");
		}

		// 根据phone查询所有未使用的短信信息，按插入时间排序
		List<SysMessage> messages = sysMessageService
				.selectList(Condition
						.create()
						.eq("phone", phone)
						.eq("state", "UNUSED").orderBy("create_date", false));
		// 如果code没有和最上面一条匹配，则验证失败
		if (messages == null || messages.isEmpty()
				|| !String.valueOf(messages.get(0).getCode()).equals(code)) {
			return ReturnDTOUtil.custom(1002, "验证码错误");
		}

		// 正确则更新状态
		for (SysMessage message : messages) {
			message.setState("USED");
		}

		sysMessageService.updateAllColumnBatchById(messages);
		return ReturnDTOUtil.success();
	}

	@ApiOperation(value = "进入短信表列表界面", notes = "进入短信表列表界面")
	@GetMapping(value = "")
	public String list(Model model) {
		return "/sys/message/list";
	}

	@ApiOperation(value = "进入短信表编辑界面", notes = "进入短信表编辑界面")
	@GetMapping(value = "/detail/{action}")
	public String detail(@PathVariable("action") String action, Model model) {
		model.addAttribute("action", action);
		return "/sys/message/detail";
	}

	@ApiOperation(value = "获取短信表列表数据", notes = "获取短信表列表数据:使用约定的DataTable")
	@PostMapping(value = "/list")
	@ResponseBody
	public DataTable<SysMessage> list(@RequestBody DataTable<SysMessage> dt) {
		return sysMessageService.pageSearch(dt);
	}

	@ApiOperation(value = "添加短信表", notes = "添加短信表")
	@PostMapping(value = "/create")
	@ResponseBody
	public ReturnDTO create(SysMessage param) {
		sysMessageService.insert(param);
		return ReturnDTOUtil.success();
	}

	@ApiOperation(value = "编辑短信表", notes = "编辑短信表")
	@PostMapping(value = "/update")
	@ResponseBody
	public ReturnDTO update(SysMessage param) {
		sysMessageService.updateById(param);
		return ReturnDTOUtil.success();
	}

	@SLog("批量删除短信表")
	@ApiOperation(value = "批量删除短信表", notes = "批量删除短信表")
	@PostMapping(value = "/delete")
	@ResponseBody
	public ReturnDTO delete(@RequestParam("ids") List<Long> ids) {
		boolean success = sysMessageService.deleteBatchIds(ids);
		if (success) {
			return ReturnDTOUtil.success();
		}
		return ReturnDTOUtil.fail();
	}
}
