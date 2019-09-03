package org.jmqtt.broker.sys;

import lombok.Data;
import org.jmqtt.broker.dispatcher.MessageDispatcher;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.config.BrokerConfig;
import org.jmqtt.common.log.LoggerName;
import org.jmqtt.remoting.netty.BrokerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SysBrokerExcutor implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(LoggerName.REMOTING);

    @Resource
    private BrokerConfig brokerConfig;

    private MessageDispatcher messageDispatcher;

    boolean stoped = false;
    private Thread thread;

    @Override
    public void run() {
        if(brokerConfig.getSysInterval() == 0) {
            log.info("系统就不发布$SYS更新");
            return;
        }

        if(brokerConfig.getSysInterval() < 60) {
            brokerConfig.setSysInterval(60);
        }
        while(!this.stoped){
            try{
                String topic = "$SYS/broker/clients/connected";
                Message message = new Message();
                message.setClientId("SysBroker");
                message.setMsgId(0);
                Map<String,Object> headers = new HashMap<>();
                headers.put(MessageHeader.TOPIC, topic);
                headers.put(MessageHeader.QOS, 0);
                message.setHeaders(headers);
                message.setType(Message.Type.PUBLISH);
                message.setPayload(BrokerStatus.getInstance().getBrokerClientConnected().toString().getBytes());
                messageDispatcher.appendMessage(message);
                Thread.sleep(brokerConfig.getSysInterval() * 1000);
            }catch(Throwable t){
                log.warn("[SysBroker] -> service has exception. ", t);
            }
        }
        log.info("[SysBroker] -> SysBrokerExcutor service end");
    }

    public void start(){
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void shutdown(){
        this.stoped = true;
    }
}
