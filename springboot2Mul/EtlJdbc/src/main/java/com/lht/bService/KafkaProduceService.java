package com.lht.bService;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lht.dModel.JdbcTaskModel;
import com.lht.util.JSONUtils;
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


    public void monichulijdbstatus(JdbcTaskModel model) {


        logger.info(JSONUtils.jsonFormattedStr(model));
        kafkaTemplate.send(topicName, "key", JSON.toJSONString(model));
        logger.info("发送完毕");

    }

}
