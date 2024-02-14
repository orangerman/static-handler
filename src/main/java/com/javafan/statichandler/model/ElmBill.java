package com.javafan.statichandler.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;

@TableName(value = "t_eml_wm_bill")
@Data
public class ElmBill {

    private int id;

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

    private int trans_status;


    public ElmBill init() {
        this.bill_amt = 0;
        this.shop_id = "123";
        return this;
    }

    public void transfer(Integer trans_status) {
        this.trans_status = trans_status;
    }
}
