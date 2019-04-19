package com.lht.bService;

import com.lht.aController.TestSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class ScheduledService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        logger.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        logger.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        logger.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }
    
    
}
