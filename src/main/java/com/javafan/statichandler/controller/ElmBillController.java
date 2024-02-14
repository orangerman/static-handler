package com.javafan.statichandler.controller;

import com.alibaba.fastjson2.JSON;
import com.javafan.statichandler.model.ElmBill;
import com.javafan.statichandler.service.ElmBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/elm/bill")
@RequiredArgsConstructor
public class ElmBillController {

    private final ElmBillService elmBillService;

    @PostMapping("/elmList")
    public String elmList() {
        List<ElmBill> elmBills = elmBillService.selectBill();
        return "";
    }

    @PostMapping("/saveBill")
    public String saveBill() {
        ElmBill elmBill = elmBillService.saveElmBill();
        log.info("elmBill:{}", JSON.toJSONString(elmBill));
        return "success";
    }

}
