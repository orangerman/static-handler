package com.javafan.statichandler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javafan.statichandler.elm.listener.ElmBillListener;
import com.javafan.statichandler.listener.ThirdAmtListener;
import com.javafan.statichandler.listener.TransBillListener;
import com.javafan.statichandler.elm.mapper.ElmBillMapper;
import com.javafan.statichandler.mapper.ThirdAmtMapper;
import com.javafan.statichandler.mapper.TransBillMapper;
import com.javafan.statichandler.elm.ElmBill;
import com.javafan.statichandler.model.ElmCompare;
import com.javafan.statichandler.model.ThirdAmt;
import com.javafan.statichandler.model.TransBill;
import com.only4play.oss.client.OssClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class StaticHandlerApplicationTests {

    @Resource
    private ThirdAmtMapper thirdAmtMapper;
    @Resource
    private ElmBillMapper elmBillMapper;

    @Resource
    private TransBillMapper transBillMapper;

    @Resource
    private OssClient ossClient;

    @Test
    public void ElmBillReadTest() {
        String fileName = "C:\\Users\\fanzq\\Desktop\\static.xlsx";
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, ElmBill.class, new ElmBillListener()).sheet().doRead();
    }

    @Test
    public void ThirdAmtReadTest() {
        String fileName = "C:\\Users\\fanzq\\Desktop\\thirdAmt.xlsx";
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, ThirdAmt.class, new ThirdAmtListener()).sheet().doRead();
    }

    @Test
    public void TransReadTest() {
        String fileName = "C:\\Users\\fanzq\\Desktop\\对账单.xlsx";
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, TransBill.class, new TransBillListener()).sheet().doRead();
    }

    @Test
    public void compare() {
        List<ElmCompare> compares = new ArrayList<>();
        String beginDate = "2024-01-04";
        String endDate = "2024-02-03";

        while (DateUtil.compare(DateUtil.parseDate(beginDate), DateUtil.parseDate(endDate)) < 0) {

            List<ThirdAmt> thirdAmtList = thirdAmtMapper.getThirdAmtBySettleDate(beginDate);
            List<String> collect = thirdAmtList.stream().map(ThirdAmt::getSettle_conf_id).collect(Collectors.toList());
            int sum = thirdAmtList.stream().mapToInt(ThirdAmt::getAmt).sum();

            List<ElmBill> elmBills = elmBillMapper.selectList(new QueryWrapper<ElmBill>().lambda()
                    .eq(ElmBill::getSettle_date, beginDate)
                    .in(ElmBill::getSettle_conf_id, collect));
            int billAmt = elmBills.stream().mapToInt(ElmBill::getBill_amt).sum();


            int transAmt = transBillMapper.findTransAmtBySettleDate(beginDate);

            ElmCompare elmCompare = new ElmCompare();
            elmCompare.setSettle_date(beginDate);
            elmCompare.setThird_amt(sum);
            elmCompare.setBill_amt(billAmt);
            elmCompare.setThird_bill_amt(sum - billAmt);
            elmCompare.setTrans_amt(transAmt);
            elmCompare.setBill_trans_amt(billAmt + transAmt);
            elmCompare.setThird_trans_amt(sum + transAmt);
            compares.add(elmCompare);
            beginDate = DateUtil.format(DateUtil.offsetDay(DateUtil.parseDate(beginDate), 1), "yyyy-MM-dd");
        }

        String fileName = "C:\\Users\\fanzq\\Desktop\\" + "廖记对账" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ElmCompare.class)
                .sheet("模板")
                .doWrite(compares);

    }

    @Test
    public void testOss() throws IOException {
        BufferedInputStream inputStream = FileUtil.getInputStream("C:\\Users\\fanzq\\Desktop\\新建 文本文档.txt");
        PutObjectResult fzq = ossClient.putObject("fzq", "demo.txt", inputStream);

    }

}
