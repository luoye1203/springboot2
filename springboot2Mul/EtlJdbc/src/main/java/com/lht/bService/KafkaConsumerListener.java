package com.lht.bService;

/**
 * Created by LHT on 2018/7/5.
 */


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaConsumerListener {
	private static final Logger LOG= Logger.getLogger(KafkaConsumerListener.class);



	@KafkaListener(topics = {"jdbcstatus"})
	public void kafkaConsumerListenterMethod(ConsumerRecord<?, ?> record) {

		if(record!=null&&record.value()!=null&&!record.value().toString().trim().equals("")){
			String message=  record.value().toString();
			try {
				System.out.println("消费到的内容: "+message);
				LOG.info("消费到的内容: "+message);
			} catch (Exception e) {
				LOG.error("data为:"+ message+ "  数据格式无效,请查证...");
			}

		}else{
			LOG.error("接收到空消息,摒弃...");
		}

	}





}