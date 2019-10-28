package com.lht.fConfig;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableKafka
public class KafkaProducerConfig {
	private static final Logger LOG= Logger.getLogger(KafkaProducerConfig.class);

	@Value("${spring.kafka.producer.servers}")
	private String servers;
	@Value("${spring.kafka.producer.retries}")
	private int retries;
	@Value("${spring.kafka.producer.batch.size}")
	private int batchSize;
	@Value("${spring.kafka.producer.linger}")
	private int linger;
	@Value("${spring.kafka.producer.buffer.memory}")
	private int bufferMemory;


	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		props.put(ProducerConfig.RETRIES_CONFIG, retries);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
		props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		LOG.info(props);
		return props;
	}

	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}
}