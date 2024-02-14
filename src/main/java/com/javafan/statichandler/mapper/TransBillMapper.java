package com.javafan.statichandler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javafan.statichandler.model.TransBill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransBillMapper extends BaseMapper<TransBill> {

    int findTransAmtBySettleDate(String settleDate);

}
