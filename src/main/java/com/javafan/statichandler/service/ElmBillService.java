package com.javafan.statichandler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javafan.statichandler.model.ElmBill;

import java.util.List;

public interface ElmBillService extends IService<ElmBill> {
    List<ElmBill> selectBill();

    ElmBill saveElmBill();

}
