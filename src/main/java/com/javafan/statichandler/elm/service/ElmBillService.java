package com.javafan.statichandler.elm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javafan.statichandler.elm.ElmBill;

import java.util.List;

public interface ElmBillService extends IService<ElmBill> {
    List<ElmBill> selectBill();

    ElmBill saveElmBill();

}
