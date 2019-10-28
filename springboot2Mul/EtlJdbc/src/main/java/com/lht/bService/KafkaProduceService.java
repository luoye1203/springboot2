package com.lht.bService;


import com.alibaba.fastjson.JSON;
import com.lht.dModel.JdbcSatusResultModel;
import com.lht.dModel.JdbcTaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProduceService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate kafkaTemplate;



    public void monichulijdbstatus(JdbcTaskModel model){

        JdbcSatusResultModel jdbcSatusResultModel=new JdbcSatusResultModel();
        jdbcSatusResultModel.setTaskId(model.getTaskId());
        jdbcSatusResultModel.setTaskVersionId(model.getTaskVersionId());


        int count = (int)(Math.random()*10000+1);
        jdbcSatusResultModel.setCountHdfs(count+"");

        if(count%2==0){
            jdbcSatusResultModel.setStatusCode("2");
        }else{
            jdbcSatusResultModel.setStatusCode("5");

        }
        logger.info(JSON.toJSONString(jdbcSatusResultModel));
        kafkaTemplate.send(topicName, "key", JSON.toJSONString(jdbcSatusResultModel));
        logger.info("发送完毕");

    }

}
