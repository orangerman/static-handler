package com.javafan.statichandler.trans;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FyTransBean extends CommonsTransBean {

    private int order_type;

}
