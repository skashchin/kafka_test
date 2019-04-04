package com.ss.test.producer.model;

import java.util.Random;

public class SsHttpModelBuilder {

    public static SsHttpMessage get() {
        return new SsHttpMessage()
                .setIp("127.0.0.1")
                .setResourceType("localhost")
                .setResponseCode(new Random().nextInt(11))
                .setTimestamp(System.currentTimeMillis())
                .setUrl("www.example.com");
    }
}