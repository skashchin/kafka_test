package com.ss.test.consumer;

import com.ss.test.common.KafkaConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;

import static com.ss.test.common.KafkaConfig.POLL_RATE_SEC;

public class ConsumerApp {
    public static void main(String[] args) {

        ElasticWriter elasticWriter = new ElasticWriter();
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer();

        int noMessageToFetch = 0;

        while (true) {
            System.out.println("Polling messages");
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofSeconds(POLL_RATE_SEC));
            if (consumerRecords.count() == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > KafkaConfig.MAX_NO_MESSAGE_FOUND_COUNT) {
                    break;
                } else {
                    System.out.println("No messages available");
                    continue;
                }
            }

            consumerRecords.forEach(record -> {
                System.out.println("Record value " + record.value());

                elasticWriter.write(record.value());
            });


            consumer.commitAsync();
        }
        consumer.close();
    }
}