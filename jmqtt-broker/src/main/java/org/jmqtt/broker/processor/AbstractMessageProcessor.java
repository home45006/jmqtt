package org.jmqtt.broker.processor;

import org.jmqtt.broker.dispatcher.InnerMessageTransfer;
import org.jmqtt.broker.dispatcher.MessageDispatcher;
import org.jmqtt.broker.plugin.PluginServer;
import org.jmqtt.common.bean.Message;
import org.jmqtt.common.bean.MessageHeader;
import org.jmqtt.common.helper.SerializeHelper;
import org.jmqtt.group.protocol.ClusterRemotingCommand;
import org.jmqtt.group.protocol.ClusterRequestCode;
import org.jmqtt.store.RetainMessageStore;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMessageProcessor {

    private MessageDispatcher messageDispatcher;
    private InnerMessageTransfer messageTransfer;
    private RetainMessageStore retainMessageStore;

    private PluginServer pluginServer;

    public AbstractMessageProcessor(MessageDispatcher messageDispatcher, RetainMessageStore retainMessageStore, InnerMessageTransfer messageTransfer, PluginServer pluginServer){
        this.messageDispatcher = messageDispatcher;
        this.retainMessageStore = retainMessageStore;
        this.messageTransfer = messageTransfer;
        this.pluginServer = pluginServer;
    }

    protected void  processMessage(Message message){
        this.messageDispatcher.appendMessage(message);
        this.pluginServer.appendMessage( message );
        boolean retain = (boolean) message.getHeader(MessageHeader.RETAIN);
        if(retain){
            int qos = (int) message.getHeader(MessageHeader.QOS);
            byte[] payload = message.getPayload();
            String topic = (String) message.getHeader(MessageHeader.TOPIC);
            //qos == 0 or payload is none,then clear previous retain message
            if(qos == 0 || payload == null || payload.length == 0){
                this.retainMessageStore.removeRetainMessage(topic);
            }else{
                this.retainMessageStore.storeRetainMessage(topic,message);
            }
        }
        dispatcherMessage2Cluster(message);
    }

    private void dispatcherMessage2Cluster(Message message){
        ClusterRemotingCommand remotingCommand = new ClusterRemotingCommand(ClusterRequestCode.SEND_MESSAGE);
        byte[] body = SerializeHelper.serialize(message);
        remotingCommand.setBody(body);
        this.messageTransfer.send2AllNodes(remotingCommand);
    }

}
