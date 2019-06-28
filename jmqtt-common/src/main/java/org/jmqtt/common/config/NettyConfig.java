package org.jmqtt.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class NettyConfig {

    private int selectorThreadNum = 3;
    private int ioThreadNum = 8;
    private int tcpBackLog = 1024;
    private boolean tcpNoDelay = false;
    private boolean tcpReuseAddr = true;
    private boolean tcpKeepAlive = false;
    private int tcpSndBuf = 65536;
    private int tcpRcvBuf = 65536;
    private boolean useEpoll = false;
    private boolean pooledByteBufAllocatorEnable = false;

    /**
     * tcp port default 1883
     */
    @Value("${jmqtt.port}")
    private int tcpPort;
    @Value("${jmqtt.startWebsocket}")
    private boolean startWebsocket;
    /**
     * websocket port default 1884
     */
    @Value("${jmqtt.websocketPort}")
    private int websocketPort;

    /**
     * max mqtt message size
     */
    @Value("${jmqtt.maxMsgSize}")
    private int maxMsgSize;

}
