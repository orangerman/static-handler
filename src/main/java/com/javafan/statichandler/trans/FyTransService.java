package com.javafan.statichandler.trans;

public class FyTransService implements TransInterface{

    @Override
    public boolean trans(CommonsTransBean transBean) {
        if (!(transBean instanceof FyTransBean)) {
            throw new IllegalArgumentException("transBean must be an instance of FyTransBean");
        }
        FyTransBean fyTransBean = (FyTransBean) transBean;
        return false;
    }
}
