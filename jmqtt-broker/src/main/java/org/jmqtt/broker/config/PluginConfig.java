package org.jmqtt.broker.config;

import org.jmqtt.broker.plugin.PluginServer;
import org.jmqtt.broker.plugin.mongo.MongodbPlugin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PluginConfig implements CommandLineRunner {

    @Resource
    private PluginServer pluginServer;
    @Resource
    private MongodbPlugin mongodbPlugin;

    @Override
    public void run(String... args) throws Exception {
        pluginServer.registerPluginHook( mongodbPlugin );
        this.pluginServer.start();
    }
}
