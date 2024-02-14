package com.javafan.statichandler.elm.event;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ElmBillEventProcessor {
    @EventListener
    public void handleElmBillTransEvent(ElmBillEvent.ElmBillTransEvent event) {
        log.info("饿了么外卖账单分账,dateTime:{},event:{}", DateUtil.now(), JSON.toJSONString(event));
    }

}
