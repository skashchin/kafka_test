package com.ss.test.producer.model;

public class SsHttpMessage {
    private String ip;
    private String url;
    private long timestamp;
    private int responseCode;
    private String resourceType;

    public String getIp() {
        return ip;
    }

    public SsHttpMessage setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SsHttpMessage setUrl(String url) {
        this.url = url;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public SsHttpMessage setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public SsHttpMessage setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public String getResourceType() {
        return resourceType;
    }

    public SsHttpMessage setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    @Override
    public String toString() {
        return "SsHttpMessage{" +
                "ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", responseCode=" + responseCode +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}