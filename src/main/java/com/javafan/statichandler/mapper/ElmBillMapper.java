package com.javafan.statichandler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javafan.statichandler.model.ElmBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ElmBillMapper extends BaseMapper<ElmBill> {
    List<ElmBill> listAllByIdElmBills();

}
