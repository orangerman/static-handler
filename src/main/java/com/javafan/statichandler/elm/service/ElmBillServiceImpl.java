package com.javafan.statichandler.elm.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javafan.statichandler.elm.event.ElmBillEvent;
import com.javafan.statichandler.elm.mapper.ElmBillMapper;
import com.javafan.statichandler.elm.ElmBill;
import com.javafan.statichandler.enums.TransStatusEnum;
import com.only4play.mybatis.support.EntityOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("elmBillService")
@RequiredArgsConstructor
public class ElmBillServiceImpl extends ServiceImpl<ElmBillMapper, ElmBill> implements ElmBillService {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<ElmBill> selectBill() {
        List<ElmBill> result = new ArrayList<>();
        baseMapper.selectList(Wrappers.emptyWrapper(),
                resultContext -> result.add(resultContext.getResultObject()));
        ElmBill elmBill = result.get(0);
        return result;
    }

    @Override
    public ElmBill saveElmBill() {
        List<String> res  = new ArrayList<>();
        ElmBill elmBill = new ElmBill();
        Optional<ElmBill> execute = EntityOperations.doCreate(baseMapper)
                .create(elmBill::init)
                .update(e -> e.transfer(TransStatusEnum.TRANS_SUCCESS))
                .successHook(e -> eventPublisher.publishEvent(new ElmBillEvent.ElmBillTransEvent(e)))
                .errorHook(e -> log.error("save error", e))
                .execute();
        return execute.orElse(null);
    }
}
