package com.lht.thread;

import com.lht.bService.KafkaProduceService;
import com.lht.dModel.JdbcTaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KafkaSendThread implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private KafkaProduceService kafkaProduceService;
    private JdbcTaskModel jdbcTaskModel;

    public KafkaSendThread(KafkaProduceService kafkaProduceService, JdbcTaskModel jdbcTaskModel) {
        this.kafkaProduceService = kafkaProduceService;
        this.jdbcTaskModel = jdbcTaskModel;
    }


    @Override
    public void run() {
        try {
            if ("1".equals(jdbcTaskModel.getStatusCode())) {
                logger.info("发送模拟执行中");
                Thread.sleep(0 * 60 * 1000);
            } else if ("2".equals(jdbcTaskModel.getStatusCode())) {
                logger.info("发送模拟执行成功");
                this.jdbcTaskModel.setCountHdfs("3232");
                Thread.sleep(1 * 30 * 1000);
            }

            this.kafkaProduceService.monichulijdbstatus(this.jdbcTaskModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
