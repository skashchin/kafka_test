package com.ss.test.common;

public class KafkaConfig {
    public static final String KAFKA_BROKERS = "localhost:9092";
    public static final Integer MESSAGE_COUNT_PER_SEC = 10;
    public static final String CLIENT_ID = "ss_producer_1";
    public static final String TOPIC_NAME = "ss_big_data_test";

    public static final String GROUP_ID_CONFIG = "consumerGroup1";
    public static final Integer MAX_NO_MESSAGE_FOUND_COUNT = 100;
    public static final String OFFSET_RESET_EARLIER = "earliest";

    public static final Integer POLL_RATE_SEC = 1;
    public static final Integer MAX_POLL_RECORDS = 1;
    public static final Integer BATCH_SIZE = 1;
}