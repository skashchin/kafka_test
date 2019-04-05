package com.ss.test.producer;

import com.ss.test.common.KafkaConfig;
import com.ss.test.producer.model.SsHttpMessage;
import org.apache.kafka.clients.producer.Producer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ProducerApp {

    public static void main(String[] args) {
        Producer<Long, SsHttpMessage> producer = ProducerCreator.createProducer();

        ScheduledExecutorService producerService = Executors.newScheduledThreadPool(1);
        MessageProducerTask messageProducerTask = new MessageProducerTask(producer, KafkaConfig.MESSAGE_COUNT_PER_SEC);

        producerService.schedule(messageProducerTask, 0, TimeUnit.SECONDS);
    }
}