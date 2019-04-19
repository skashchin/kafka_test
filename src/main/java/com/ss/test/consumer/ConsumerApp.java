package com.ss.test.consumer;

import com.ss.test.common.KafkaConfig;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.ss.test.common.KafkaConfig.POLL_RATE_SEC;

public class ConsumerApp {
    public static void main(String[] args) {

        ElasticWriter elasticWriter = new ElasticWriter();
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer();

        int noMessageToFetch = 0;

        while (true) {
            System.out.println("Polling messages");
            long timePollStart = System.currentTimeMillis();
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofSeconds(POLL_RATE_SEC));
            long timePollEnd = System.currentTimeMillis();
            int recordCount = consumerRecords.count();

            System.out.println("Polling " + recordCount + " took " + (timePollEnd - timePollStart) + " ms");

            if (recordCount == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > KafkaConfig.MAX_NO_MESSAGE_FOUND_COUNT) {
                    break;
                } else {
                    System.out.println("No messages available");
                    continue;
                }
            }

            System.out.println("Consumer rec count: " + recordCount);

            long timeRecStart = System.currentTimeMillis();
            List<String> bulkList = new ArrayList<>();
            consumerRecords.forEach(record -> {
                bulkList.add(record.value());
            });

            elasticWriter.writeAll(bulkList);

            long timeRecEnd = System.currentTimeMillis();
            long totalRunTime = timeRecEnd - timeRecStart;
            float recPerSec = ((float) recordCount / totalRunTime) * 1000;

            System.out.println("Writing " + recordCount + " took " + totalRunTime + " ms, " + recPerSec + " msg/sec");



            consumer.commitAsync();
        }
        consumer.close();
    }
}