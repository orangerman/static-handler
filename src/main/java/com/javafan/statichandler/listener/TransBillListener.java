package com.javafan.statichandler.listener;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.javafan.statichandler.mapper.TransBillMapper;
import com.javafan.statichandler.model.TransBill;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TransBillListener implements ReadListener<TransBill> {

    private static final int BATCH_COUNT = 100;

    private List<TransBill> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(TransBill transBill, AnalysisContext analysisContext) {
        log.info("transBill:{}", JSON.toJSONString(transBill));
        transBill.setBill_date(transBill.getRemark().substring(5, 15));
        transBill.setSettle_date(DateUtil.format(DateUtil.offsetDay(DateUtil.parseDate(transBill.getBill_date()), 3), "yyyy-MM-dd"));
        cachedDataList.add(transBill);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    private void saveData() {
        TransBillMapper transBillMapper = SpringUtil.getBean("transBillMapper");
        for (TransBill transBill : cachedDataList) {
            transBillMapper.insert(transBill);
        }

    }
}
