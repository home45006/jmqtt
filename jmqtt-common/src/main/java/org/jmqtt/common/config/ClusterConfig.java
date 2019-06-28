package org.jmqtt.common.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * cluster group config
 */
@Data
@ToString
@Component
public class ClusterConfig {

    @Value("${jmqtt.currentNodeIp}")
    private String currentNodeIp = "";
    @Value("${jmqtt.nodeName}")
    private String nodeName;
    @Value("${jmqtt.groupServerPort}")
    private int groupServerPort;
    /**
     * cluster node : ip1:port1;ip2;port2
     */
    @Value("${jmqtt.groupNodes}")
    private String groupNodes;
    private long timeoutMills = 3000L;

    /**
     * if cluster transfer message body size > 4069,compress the message body
     */
    private long compressMaxSize = 4069;

    /**
     * group netty config
     */
    private int groupSelectorThreadNum = 3;
    private int groupIoThreadNum = 8;
    private int groupTcpBackLog = 1024;
    private boolean groupTcpNoDelay = false;
    private boolean groupTcpReuseAddr = true;
    private boolean groupTcpKeepAlive = false;
    private int groupTcpSndBuf = 65536;
    private int groupTcpRcvBuf = 65536;
    private boolean groupUseEpoll = false;
    private boolean groupPooledByteBufAllocatorEnable = false;

}
