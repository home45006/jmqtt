package org.jmqtt.broker.plugin;

import org.jmqtt.common.bean.Message;

/**
 * @author yangyong
 */
public interface PluginHook {

    /**
     * 接收消息
     *
     * @param message
     */
    void receiveMessage(Message message);
}
