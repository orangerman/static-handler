package com.javafan.statichandler.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class ElmCompare {

    @ExcelProperty(value = "结算日期",index = 0)
    private String settle_date;

    @ExcelProperty(value = "三方入金金额",index = 1)
    private int third_amt;

    @ExcelProperty(value = "账单金额",index = 2)
    private int bill_amt;

    @ExcelProperty(value = "入金与账单差异",index = 3)
    private int third_bill_amt;

    @ExcelProperty(value = "分账金额",index = 4)
    private int trans_amt;

    @ExcelProperty(value = "入金与分账差异",index = 5)
    private int third_trans_amt;

    @ExcelProperty(value = "账单与分账差异",index = 6)
    private int bill_trans_amt;


}
