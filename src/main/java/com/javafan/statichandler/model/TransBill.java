package com.javafan.statichandler.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_trans_bill")
public class TransBill {

    @TableId
    private int id;

    @ExcelProperty(value = "分账订单号")
    private String order_no;

    @ExcelProperty(value = "分账金额(元)")
    private int amt;

    @ExcelProperty(value = "分账申请时间")
    private String trans_time;


    @ExcelProperty(value = "入账账户编号")
    private String account_no;

    @ExcelProperty(value = "入账一账通名称")
    private String account_name;

    @ExcelProperty(value = "备注")
    private String remark;

    @ExcelIgnore
    private String bill_date;

    @ExcelIgnore
    private String settle_date;

}
