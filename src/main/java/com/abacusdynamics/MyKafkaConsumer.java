package com.abacusdynamics;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@Getter
@Setter
public class MyKafkaConsumer {

    private String payload;

    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void processMessage(ConsumerRecord<?, ?> content){
        log.info("Message received:'{}'", content.toString());
        setPayload(content.toString());
//        System.out.println(latch.getCount());
        latch.countDown();
//        System.out.println(latch.getCount());

    }

}
