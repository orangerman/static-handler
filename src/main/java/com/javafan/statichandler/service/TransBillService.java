package com.javafan.statichandler.service;

import com.javafan.statichandler.mapper.TransBillMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TransBillService {

    @Resource
    private TransBillMapper transBillMapper;


}
