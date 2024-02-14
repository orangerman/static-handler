package com.javafan.statichandler.elm;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.javafan.statichandler.enums.TransStatusEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;

@TableName(value = "t_eml_wm_bill")
@Data
public class ElmBill {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ExcelProperty(value = "门店ID")
    @NotBlank
    private String shop_id;

    @ExcelProperty(value = "门店名称")
    private String shop_name;

    @ExcelProperty(value = "结算金额")
    private int bill_amt;

    @ExcelProperty(value = "账单日期")
    private String bill_date;

    @ExcelProperty(value = "结算日期")
    private String settle_date;

    @ExcelProperty(value = "结算入账ID")
    private String settle_conf_id;

    private TransStatusEnum trans_status;


    public ElmBill init() {
        this.bill_amt = 0;
        this.shop_id = "123";
        return this;
    }

    public void transfer(TransStatusEnum trans_status) {
        this.trans_status = trans_status;
    }
}
