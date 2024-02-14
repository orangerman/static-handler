package com.javafan.statichandler.elm.event;

import com.javafan.statichandler.elm.ElmBill;
import lombok.Value;

public interface ElmBillEvent {

    @Value
    class ElmBillTransEvent {
        ElmBill elmBill;
    }

}
