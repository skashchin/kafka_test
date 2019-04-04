package com.ss.test.producer;

import com.ss.test.common.KafkaConfig;
import com.ss.test.producer.model.SsHttpMessage;
import com.ss.test.producer.model.SsHttpModelBuilder;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageProducerTask implements Runnable {
    private Producer<Long, SsHttpMessage> producer;
    private int messageCount;

    public MessageProducerTask(Producer<Long, SsHttpMessage> producer, int messageCount) {
        this.producer = producer;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        List<ProducerRecord<Long, SsHttpMessage>> records = createRecords(messageCount);

        records.forEach(r -> {
            try {
                RecordMetadata metadata = producer.send(r).get();
                System.out.println("Record sent to partition " + metadata.partition() + " with offset "
                        + metadata.offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    private List<ProducerRecord<Long, SsHttpMessage>> createRecords(int count) {
        List<ProducerRecord<Long, SsHttpMessage>> records = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            ProducerRecord<Long, SsHttpMessage> record = new ProducerRecord<>(KafkaConfig.TOPIC_NAME,
                    SsHttpModelBuilder.get());

            records.add(record);
        }

        return records;
    }
}