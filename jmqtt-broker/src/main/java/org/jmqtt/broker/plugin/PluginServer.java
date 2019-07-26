package org.jmqtt.broker.plugin;

import org.jmqtt.common.bean.Message;

/**
 * @author yongyang
 */
public interface PluginServer {

    void start();

    void registerPluginHook(PluginHook pluginHook);

    boolean appendMessage(Message message);

    void shutdown();
}
