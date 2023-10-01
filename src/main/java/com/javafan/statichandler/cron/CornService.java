package com.javafan.statichandler.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CornService {

    /**
     * 一分钟一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void demoCronTask() {
        log.info("com.javafan.statichandler.cron.CornService.demoCronTask");
    }

}
