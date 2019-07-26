package org.jmqtt.broker.plugin;

import org.jmqtt.common.bean.Message;
import org.jmqtt.common.helper.RejectHandler;
import org.jmqtt.common.helper.ThreadFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yongyang
 */
@Component
public class DefaultPluginServer implements PluginServer {

    private static final Logger log = LoggerFactory.getLogger( "DefaultNettyPluginServer" );

    boolean stoped = false;

    private ThreadPoolExecutor pollMessageThread;

    private Queue<Message> messageQueue = new LinkedBlockingQueue( 100000 );

    private List<PluginHook> pluginHookList = new ArrayList<>();

    @Override
    public void registerPluginHook(PluginHook pluginHook) {
        pluginHookList.add( pluginHook );
    }

    @Override
    public boolean appendMessage(Message message) {
        boolean isNotFull = messageQueue.offer(message);
        if (!isNotFull) {
            log.warn("[PubMessage] -> the buffer queue is full");
        }
        return isNotFull;
    }

    @Override
    public void start() {
        int coreThreadNum = Runtime.getRuntime().availableProcessors();
        this.pollMessageThread = new ThreadPoolExecutor( coreThreadNum * 2,
                coreThreadNum * 2,
                60 * 1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>( 100000 ),
                new ThreadFactoryImpl( "pollMessagePlugin" ),
                new RejectHandler( "plugin", 100000 ) );
        pollMessageThread.submit( new AsyncMessageTransfer() );
    }

    @Override
    public void shutdown() {
        this.pollMessageThread.shutdown();
    }

    class AsyncMessageTransfer implements Runnable {
        @Override
        public void run() {
            while (!stoped) {
                if (!pluginHookList.isEmpty() && !messageQueue.isEmpty()) {
                    Message message = messageQueue.poll();
                    pluginHookList.forEach( pluginHook -> pluginHook.receiveMessage( message ) );
                } else {
                    try {
                        TimeUnit.SECONDS.sleep( 5 );
                    } catch (InterruptedException e) {
                        log.error( "[DefaultNettyPluginServer err : {}]", e );
                    }
                }
            }
        }
    }

}
