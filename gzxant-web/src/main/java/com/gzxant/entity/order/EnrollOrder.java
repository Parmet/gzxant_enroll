package com.gzxant.entity.order;

import com.baomidou.mybatisplus.annotations.TableName;
import com.gzxant.base.entity.DataEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author tecty
 * @since 2018-07-14
 */
@TableName("enroll_order")
public class EnrollOrder extends DataEntity<EnrollOrder> {

    private static final long serialVersionUID = 1L;

    public static final String SYS_CONFIG_MONEY_KEY = "ENROLL_MONEY";

    private String openid;
    private String name;
    private BigDecimal money;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EnrollOrder{" +
                "openid=" + openid +
                ", name=" + name +
                ", money=" + money +
                ", updateId=" + updateId +
                "}";
    }
}
