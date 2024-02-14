package com.javafan.statichandler.listener;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.javafan.statichandler.mapper.ElmBillMapper;
import com.javafan.statichandler.mapper.ThirdAmtMapper;
import com.javafan.statichandler.model.ElmBill;
import com.javafan.statichandler.model.ThirdAmt;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ThirdAmtListener implements ReadListener<ThirdAmt> {

    private static final int BATCH_COUNT = 100;

    private List<ThirdAmt> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    @Override
    public void invoke(ThirdAmt thirdAmt, AnalysisContext analysisContext) {
        thirdAmt.setSettle_conf_id(thirdAmt.getSettle_conf_id().substring(9));
        log.info("thirdAmt:{}", JSON.toJSONString(thirdAmt));
        cachedDataList.add(thirdAmt);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }


    }

    private void saveData() {
        ThirdAmtMapper thirdAmtMapper = (ThirdAmtMapper) SpringUtil.getBean("thirdAmtMapper");
        for (ThirdAmt thirdAmt : cachedDataList) {
            thirdAmtMapper.insert(thirdAmt);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }
}
