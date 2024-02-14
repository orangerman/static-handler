package com.javafan.statichandler.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_third_amt")
public class ThirdAmt {

    private int id;

    @ExcelProperty(value = "结算金额")
    private int amt;

    @ExcelProperty(value = "结算日期")
    private String settle_date;

    @ExcelProperty(value = "结算入账ID")
    private String settle_conf_id;

    @ExcelProperty(value = "结算订单号")
    private String order_no;

}
