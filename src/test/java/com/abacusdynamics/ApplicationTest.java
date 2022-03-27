package com.abacusdynamics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ApplicationTest {

    @Autowired
    MyKafkaProducer producer;

    @Autowired
    MyKafkaConsumer consumer;

    @Value(("${kafka.topic}"))
    private String topic;

    @Test
    void contextLoads() {

    }

    @Test
    void canProduceMessageOnEmbeddedKafkaAndConsumeIt() throws Exception {
        producer.send(topic, "Producing test message");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//        System.out.println(consumer.getPayload());
        assertThat(consumer.getLatch().getCount(), equalTo(0L));
        assertThat(consumer.getPayload(), containsString("Producing test message"));
    }
}