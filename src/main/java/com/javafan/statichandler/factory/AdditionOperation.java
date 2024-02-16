package com.javafan.statichandler.factory;

import org.springframework.beans.factory.InitializingBean;

public class AdditionOperation implements Operation, InitializingBean {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public void afterPropertiesSet() {
        OperationFactory.registerOperation("add", this);
    }
}