package com.javafan.statichandler.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javafan.statichandler.mapper.ElmBillMapper;
import com.javafan.statichandler.model.ElmBill;
import com.only4play.mybatis.support.EntityOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("elmBillService")
public class ElmBillServiceImpl extends ServiceImpl<ElmBillMapper, ElmBill> implements ElmBillService {

    @Override
    public List<ElmBill> selectBill() {
        List<ElmBill> result = new ArrayList<>();
        baseMapper.selectList(Wrappers.emptyWrapper(),
                resultContext -> result.add(resultContext.getResultObject()));
        return result;
    }

    @Override
    public ElmBill saveElmBill() {
        ElmBill elmBill = new ElmBill();
        Optional<ElmBill> execute = EntityOperations.doCreate(baseMapper)
                .create(elmBill::init)
                .update(e -> e.transfer(1))
                .successHook(e -> log.info("save success"))
                .errorHook(e -> log.error("save error", e))
                .execute();
        return execute.orElse(null);
    }
}
