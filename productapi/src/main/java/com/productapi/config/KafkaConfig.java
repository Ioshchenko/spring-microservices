package com.productapi.config;

import com.google.common.base.Ticker;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${kafka.topic}")
    private String topicSuffix;

    @Value("${kafka.group}")
    private String groupId;

    @Value("${kafka.user}")
    private String user;

    @Value("${kafka.pswd}")
    private String pswd;

    @Value("${kafka.servers}")
    private String servers;

    @Bean(name = "topic")
    public String topic() {
        return user + "-" + topicSuffix;
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, List<String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, List<String>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, List<String>> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }


    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 250);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 600000);
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("sasl.jaas.config", getJaas());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.cryptobot.kafka.dto");
        return props;

    }

    private String getJaas() {
        String jaasTemplate =
                "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        return String.format(
                jaasTemplate,
                user,
                pswd);
    }
}
