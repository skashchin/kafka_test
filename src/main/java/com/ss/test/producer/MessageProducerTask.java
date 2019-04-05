package com.ss.test.producer;

import com.ss.test.common.KafkaConfig;
import com.ss.test.producer.model.SsHttpMessage;
import com.ss.test.producer.model.SsHttpModelBuilder;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;

public class MessageProducerTask implements Runnable {
    private Producer<Long, SsHttpMessage> producer;
    private int messageCount;

    public MessageProducerTask(Producer<Long, SsHttpMessage> producer, int messageCount) {
        this.producer = producer;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        long timeCreate = System.currentTimeMillis();
        List<ProducerRecord<Long, SsHttpMessage>> records = createRecords(messageCount);
        long timeCreateEnd = System.currentTimeMillis();

        System.out.println("Creating " + messageCount + " records took " + (timeCreateEnd - timeCreate));

        long timeRecStart = System.currentTimeMillis();
        records.forEach(r -> {
            producer.send(r);
        });

        long timeRecEnd = System.currentTimeMillis();

        long totalRunTime = timeRecEnd - timeRecStart;
        System.out.println("Recording of " + messageCount + " records took " + totalRunTime);
        System.out.println("Recorded " + (((float) messageCount / totalRunTime) * 1000) + " msg/sec");
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