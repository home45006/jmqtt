package org.jmqtt.remoting.netty;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName BrokerStatus
 * @Description: TODO
 * @Author: david
 * @create: 2019-08-29 15:55
 **/
@Data
public class BrokerStatus {

    private static BrokerStatus instance = null;

    private BrokerStatus() {

    }

    public static BrokerStatus getInstance() {
        // 先判断实例是否存在，若不存在再对类对象进行加锁处理
        if (instance == null) {
            synchronized (BrokerStatus.class) {
                if (instance == null) {
                    instance = new BrokerStatus();
                }
            }
        }
        return instance;
    }

    private AtomicLong brokerClientConnected = new AtomicLong(0L);
}
