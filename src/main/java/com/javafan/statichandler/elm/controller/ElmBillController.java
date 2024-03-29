package com.javafan.statichandler.elm.controller;

import com.alibaba.fastjson2.JSON;
import com.javafan.statichandler.annotation.Logs;
import com.javafan.statichandler.elm.ElmBill;
import com.javafan.statichandler.elm.service.ElmBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/elmList/{id}")
    @Logs
    public String elmList(@PathVariable(value = "id") String path) {
        List<ElmBill> elmBills = elmBillService.selectBill();
        return "success";
    }

    @PostMapping("/saveBill")
    public String saveBill() {
        ElmBill elmBill = elmBillService.saveElmBill();
        log.info("elmBill:{}", JSON.toJSONString(elmBill));
        return "success";
    }

}
