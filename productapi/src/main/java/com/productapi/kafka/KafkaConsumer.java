package com.productapi.kafka;

import com.productapi.service.ProductStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class KafkaConsumer {
    @Autowired
    private ProductStatisticsService productStatisticsService;


    @KafkaListener(topics = "#{topic.toString()}")
    public void receive(List<String> data) {
        log.info("Save product statistics");
        data.forEach(p -> productStatisticsService.increment(p));
    }
}
