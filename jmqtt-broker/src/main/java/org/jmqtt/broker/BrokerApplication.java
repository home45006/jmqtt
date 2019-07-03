package org.jmqtt.broker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;


/**
 * <功能说明>:
 *
 * @author yangyong
 * @date 2019-06-28
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.jmqtt.*")
public class BrokerApplication implements CommandLineRunner {

    @Resource
    private BrokerController brokerController;

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        brokerController.start();
    }
}
