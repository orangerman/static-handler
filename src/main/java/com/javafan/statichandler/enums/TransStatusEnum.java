package com.javafan.statichandler.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.only4play.common.constants.BaseEnum;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum TransStatusEnum implements BaseEnum<TransStatusEnum> {
    WAIT_TRANS(0, "待分账"),
    TRANS_ING(1, "分帐中"),
    TRANS_SUCCESS(2, "分账成功"),
    TRANS_FAIL(3, "分账失败"),
    ;

    @EnumValue
    @JsonValue
    private final int code;
    private final String desc;

    TransStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static Optional<TransStatusEnum> of(int code) {
        return Arrays.stream(TransStatusEnum.values())
                .filter(s -> Objects.equals(s.getCode(), code)).findFirst();
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.desc;
    }
}
