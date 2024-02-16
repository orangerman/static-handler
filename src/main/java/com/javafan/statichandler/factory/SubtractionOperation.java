package com.javafan.statichandler.factory;

import org.springframework.beans.factory.InitializingBean;

public class SubtractionOperation implements Operation, InitializingBean {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OperationFactory.registerOperation("subtract", this);
    }
}