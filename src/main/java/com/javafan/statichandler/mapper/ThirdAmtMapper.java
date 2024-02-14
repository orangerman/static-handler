package com.javafan.statichandler.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javafan.statichandler.model.ThirdAmt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ThirdAmtMapper extends BaseMapper<ThirdAmt> {

    List<ThirdAmt> getThirdAmtBySettleDate(String settle_date);

}
