package com.gzxant.controller.enroll.api;

import com.gzxant.base.controller.BaseController;
import com.gzxant.base.entity.ReturnDTO;
import com.gzxant.entity.enroll.enter.EnrollEnter;
import com.gzxant.entity.enroll.personnel.EnrollPersonnel;
import com.gzxant.entity.order.EnrollOrder;
import com.gzxant.service.order.IEnrollOrderService;
import com.gzxant.util.PasswordUtils;
import com.gzxant.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gzxant.service.personnel.IEnrollPersonnelService;
import com.gzxant.service.enroll.enter.IEnrollEnterService;
import java.io.IOException;
import java.math.BigDecimal;
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

    /**
     * 报名接口
     *
     * @param model
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/enroll", method = {RequestMethod.POST})
    public ReturnDTO enroll(Model model, EnrollPersonnel param) throws IOException {
        EnrollEnter enrollEnter = new EnrollEnter();
        if (param == null) {
            return new ReturnDTO(PARARM_FAIL, "参数不能为空");
        }

        if (StringUtils.isEmpty(param.getName()) || StringUtils.isChinese(param.getName())) {
            return new ReturnDTO(PARARM_FAIL, "姓名必须是汉字");
        }
        if (StringUtils.isEmpty(param.getPassword())||StringUtils.isPassword(param.getPassword())) {
            return new ReturnDTO(PARARM_FAIL, "密码不能小于6位数");
        }
        if (StringUtils.isMobile(param.getPhone())||StringUtils.isEmpty(param.getPhone())) {
            return new ReturnDTO(PARARM_FAIL, "请输入正确的手机号码");
        }
        if (StringUtils.isIDCard(param.getIdCard())||StringUtils.isEmpty(param.getIdCard())) {
            return new ReturnDTO(PARARM_FAIL, "请输入18或15位的身份证号码");
        }
        if (StringUtils.isEmpty(param.getPlace())) {
            return new ReturnDTO(PARARM_FAIL, "报名地点不能为空");
        }

        param.setCreateId(Long.parseLong(param.getPhone()));
        param.setUpdateId(Long.parseLong(param.getPhone()));
        boolean isFlag=enrollPersonnelService.insertBean(param);
        if (!isFlag) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "系统繁忙，请重试");
        }
        enrollEnter.setPlace(param.getPlace());
        enrollEnter.setName(param.getName());
        enrollEnter.setType("缴费");
        enrollEnter.setState("Y");
        enrollEnter.setNumbers(param.getNumbers());
        enrollEnter.setCreateId(Long.parseLong(param.getPhone()));
        enrollEnter.setUpdateId(Long.parseLong(param.getPhone()));

        EnrollOrder order = new EnrollOrder();
        order.setMoney(BigDecimal.valueOf(0.01));
        order.setOpenid(param.getOpenid());
        order.setName(param.getName());
        order.setCreateId(Long.parseLong(param.getPhone()));
        order.setUpdateId(Long.parseLong(param.getPhone()));
        boolean orderFlag=orderService.insert(order);
        if(!orderFlag){
            return new ReturnDTO(NOT_RESULT_SUCCESS, "生成订单失败!");
        }

        boolean isFlag1 = enrollEnterService.insertBean(enrollEnter);
        if (!isFlag1) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "系统繁忙，请重试");
        }
        return new ReturnDTO(RESULT_SUCCESS, "插入成功");
    }


    /**
     * 登录接口
     *
     * @param model
     * @param name
     * @param password
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ReturnDTO login(Model model, @RequestParam("name") String name, @RequestParam("password") String password) throws IOException {
        if (StringUtils.isEmpty(name)) {
            return new ReturnDTO(PARARM_FAIL, "用户名不能为空");
        }
        if (StringUtils.isEmpty(password)||StringUtils.isPassword(password)) {
            return new ReturnDTO(PARARM_FAIL, "密码不能为空");
        }
        String md5password = PasswordUtils.entryptPassword(password);
        EnrollPersonnel enrollPersonnel = enrollPersonnelService.login(name, md5password);
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
     * @param model
     * @param numbers
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/enter/{numbers}", method = {RequestMethod.GET})
    public ReturnDTO findbyIdEnterdate(Model model, @RequestParam("numbers") String numbers) throws IOException {
        if (StringUtils.isEmpty(numbers)) {
            return new ReturnDTO(PARARM_FAIL, "参数不能为空");
        }
        EnrollEnter enrollEnter = enrollEnterService.findbyIdEnterdate(numbers);
        if (enrollEnter == null) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "没有查到数据");
        }
        return new ReturnDTO(RESULT_SUCCESS, "成功", enrollEnter);
    }

    /**
     * 不全参赛者管理信息
     *
     * @param model
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/allEnter", method = {RequestMethod.GET})
    public ReturnDTO findbyALlEnterdate(Model model) throws IOException {
        List<EnrollEnter> list = enrollEnterService.selectList(null);
        //返回数据到前端
        if (list == null) {
            return new ReturnDTO(NOT_RESULT_SUCCESS, "没有查到数据");
        }
        return new ReturnDTO(RESULT_SUCCESS, "成功", list);
    }


}
