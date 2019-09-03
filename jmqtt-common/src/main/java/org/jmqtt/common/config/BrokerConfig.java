package org.jmqtt.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class BrokerConfig {

    private String jmqttHome = System.getProperty("jmqttHome", System.getenv("JMQTT_HOME"));

    @Value("${jmqtt.version}")
    private String version;

    @Value("${jmqtt.sysInterval}")
    private Integer sysInterval;

    @Value("${jmqtt.allowAnonymous}")
    private Boolean allowAnonymous;

    @Value("${jmqtt.passwordFile}")
    private String passwordFile;

    private int pollThreadNum = Runtime.getRuntime().availableProcessors() * 2;
}
