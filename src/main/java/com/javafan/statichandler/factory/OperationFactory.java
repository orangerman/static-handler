package com.javafan.statichandler.factory;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {
    private static final Map<String, Operation> operationMap = new HashMap<>();

    public static void registerOperation(String operationType, Operation operation) {
        operationMap.put(operationType, operation);
    }

    public static Operation createOperation(String operationType) {
        Operation operation = operationMap.get(operationType);
        if (operation == null) {
            throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }
        return operation;
    }
}