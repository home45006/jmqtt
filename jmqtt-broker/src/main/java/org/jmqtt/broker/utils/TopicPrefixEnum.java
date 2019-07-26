package org.jmqtt.broker.utils;

/**
 * @author yangyong
 */

public enum TopicPrefixEnum {

    PUBLIC_TOPIC( "/up/" ),
    SUBSCRIBE_TOPIC( "/down/" );

    private String value;

    TopicPrefixEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
