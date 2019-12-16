package com.lht.myquartz.bService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoverService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sayLove(){
        logger.info("(๑′ᴗ‵๑)Ｉ Lᵒᵛᵉᵧₒᵤ❤ I love you!!!!!!!!!!!");
    }
}
