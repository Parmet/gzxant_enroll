package com.gzxant.controller.enroll.api;

import com.baomidou.mybatisplus.mapper.Condition;
import com.gzxant.base.controller.BaseController;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.common.entity.config.SysConfig;
import com.gzxant.common.service.config.ISysConfigService;
import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.entity.order.EnrollOrder;
import com.gzxant.service.order.IEnrollOrderService;
import com.gzxant.util.PasswordUtils;
import com.gzxant.util.ReturnDTOUtil;
import com.gzxant.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.gzxant.service.personnel.IEnrollPersonnelService;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class ApiController extends BaseController {
    private static String Prefix="MB000";
    private static int RESULT_SUCCESS = 1000;
    private static int PARARM_FAIL = 1001;
    private static int NOT_RESULT_SUCCESS = 1002;
    @Autowired
    private IEnrollPersonnelService enrollPersonnelService;

    @Autowired
    private IEnrollEnterService enrollEnterService;

    @Autowired
    private IEnrollOrderService orderService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 报名接口
     *
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/enroll", method = {RequestMethod.POST})
    @Transactional(readOnly = false)
    public ReturnDTO enroll(EnrollPersonnel param) {
        EnrollEnter enrollEnter = new EnrollEnter();
        if (param == null) {
            return new ReturnDTO(PARARM_FAIL, "参数不能为空");
        }

        if (StringUtils.isEmpty(param.getName())) {
            return new ReturnDTO(PARARM_FAIL, "姓名不能为空");
        }
        if (StringUtils.isEmpty(param.getPassword())
                || (param.getPassword().length() < 6
                || param.getPassword().length() > 16)) {
            return new ReturnDTO(PARARM_FAIL, "密码参数不正确");
        }
        if (StringUtils.isEmpty(param.getPhone())
                || !StringUtils.isMobile(param.getPhone())) {
            return new ReturnDTO(PARARM_FAIL, "请输入正确的手机号码");
        }
        if (StringUtils.isEmpty(param.getIdCard())
                || !StringUtils.isIDCard(param.getIdCard())) {
            return new ReturnDTO(PARARM_FAIL, "请输入18或15位的身份证号码");
        }
        if (StringUtils.isEmpty(param.getPlace())) {
            return new ReturnDTO(PARARM_FAIL, "报名地点不能为空");
        }

        int count = enrollPersonnelService
                .selectCount(Condition.create().eq("phone", param.getPhone()));
        if (count > 0) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "您已报名，请勿重复报名，详情请咨询【唱响春天广东赛区】公众号");
        }

        count = orderService.selectCount(Condition.create().eq("openid", param.getOpenid()));
        if (count > 0) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "同个微信号只能提交一次报名");
        }

        param.setCreateId(Long.parseLong(param.getPhone()));
        param.setUpdateId(Long.parseLong(param.getPhone()));
        boolean isFlag=enrollPersonnelService.insertBean(param);
        if (!isFlag) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "报名失败，详情请咨询【唱响春天广东赛区】公众号");
        }

        enrollEnter.setPlace(param.getPlace());
        enrollEnter.setName(param.getName());
        enrollEnter.setType("缴费");
        enrollEnter.setState("N");
        enrollEnter.setNumbers(param.getNumbers());
        enrollEnter.setCreateId(Long.parseLong(param.getPhone()));
        enrollEnter.setUpdateId(Long.parseLong(param.getPhone()));

        EnrollOrder order = new EnrollOrder();
        SysConfig config = configService
                .selectOne(Condition.create().eq("jkey", order.SYS_CONFIG_MONEY_KEY));
        order.setMoney(BigDecimal.valueOf(Double.parseDouble(config.getValue())));
        order.setOpenid(param.getOpenid());
        order.setName(param.getName());
        order.setCreateId(Long.parseLong(param.getPhone()));
        order.setUpdateId(Long.parseLong(param.getPhone()));
        boolean orderFlag=orderService.insert(order);
        if(!orderFlag){
            return new ReturnDTO(NOT_RESULT_SUCCESS, "报名失败，详情请咨询【唱响春天广东赛区】公众号");
        }

        boolean isFlag1 = enrollEnterService.insertBean(enrollEnter);
        if (!isFlag1) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "报名失败，详情请咨询【唱响春天广东赛区】公众号");
        }

        return new ReturnDTO(RESULT_SUCCESS, "插入成功");
    }

    @RequestMapping(value = "/enroll/check/phone", method = {RequestMethod.GET})
    public Boolean checkPhone(@RequestParam("phone") String phone) {
        if (StringUtils.isBlank(phone)
                || !StringUtils.isMobile(phone)) {
            return false;
        }

        if (enrollPersonnelService.checkPhone(null, phone)) {
            return false;
        }

        return true;
    }


    /**
     * 登录接口
     *
     * @param name
     * @param password
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ReturnDTO login(@RequestParam("name") String name,
                           @RequestParam("password") String password) {
        if (StringUtils.isEmpty(name)) {
            return new ReturnDTO(PARARM_FAIL, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            return new ReturnDTO(PARARM_FAIL, "密码不能为空");
        }

//        String md5password = PasswordUtils.entryptPassword(password);
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
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/enter/{id}", method = {RequestMethod.GET})
    public ReturnDTO findbyIdEnterdate(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return new ReturnDTO(PARARM_FAIL, "参数不能为空");
        }

        EnrollPersonnel person = enrollPersonnelService.selectById(id);
        if (person == null || person.getId() == null) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "用户不存在");
        }

        EnrollEnter enrollEnter = enrollEnterService.findbyIdEnterdate(person.getNumbers());
        if (enrollEnter == null) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "用户暂未报名");
        }

        Map map = new HashMap();
        map.put("info", person);
        map.put("enter", enrollEnter);
        return new ReturnDTO(RESULT_SUCCESS, "成功", map);
    }

    /**
     * 不全参赛者管理信息
     *
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/allEnter", method = {RequestMethod.GET})
    public ReturnDTO findbyALlEnterdate() {
        List<EnrollEnter> list = enrollEnterService.selectList(null);
        //返回数据到前端
        if (list == null) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "没有查到数据");
        }
        return new ReturnDTO(RESULT_SUCCESS, "成功", list);
    }


}
