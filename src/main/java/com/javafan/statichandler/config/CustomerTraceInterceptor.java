package com.javafan.statichandler.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.lang.Nullable;

@Setter
@Slf4j
public class CustomerTraceInterceptor extends CustomizableTraceInterceptor {

    boolean logExceptionStackTrace = true;

    protected void writeToLog(Log logger, String message, @Nullable Throwable ex) {
        if (ex != null && this.logExceptionStackTrace) {
            logger.info(message, ex);
        } else {
            logger.info(message);
        }
    }

    protected boolean isLogEnabled(Log logger) {
        return logger.isInfoEnabled();
    }

}
