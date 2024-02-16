package com.javafan.statichandler.elm.listener;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.javafan.statichandler.elm.ElmBill;
import com.javafan.statichandler.elm.mapper.ElmBillMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ElmBillListener implements ReadListener<ElmBill> {

    private static final int BATCH_COUNT = 100;

    private List<ElmBill> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(ElmBill elmBill, AnalysisContext analysisContext) {
        log.info("elmBill:{}", JSON.toJSONString(elmBill));
        cachedDataList.add(elmBill);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }

    }

    private void saveData() {
        ElmBillMapper elmBillMapper = (ElmBillMapper) SpringUtil.getBean("elmBillMapper");
        for (ElmBill elmBill : cachedDataList) {
            elmBillMapper.insert(elmBill);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
}
