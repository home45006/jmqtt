package org.jmqtt.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Data
@Component
public class StoreConfig {


    /**
     * store type default 1:rocksdb  2.redis  3.in memory
     */
    @Value("${jmqtt.storeType}")
    private int storeType;


    /**
     * rocksdb store configuration
     */
    @Value("${jmqtt.rocksDbPath}")
    private String rocksDbPath = System.getProperty("user.home", System.getenv("user.home")) + File.separator + "rocksdb";
    private int maxBackgroundFlushes = 10;
    private int maxBackgroundCompactions = 10;
    private int maxOpenFiles = 2048;
    private int maxSubcompactions = 10;
    private int baseBackGroundCompactions = 10;
    private int useFixedLengthPrefixExtractor = 10;
    private int writeBufferSize = 128;
    private int maxWriteBufferNumber = 10;
    private int level0SlowdownWritesTrigger = 30;
    private int level0StopWritesTrigger = 50;
    private int maxBytesForLevelBase = 512;
    private int targetFileSizeBase = 128;
    private int delayedWriteRate = 64;


    /**
     * redis store configuration
     */
    private String nodes;
    private String password;
    private Integer maxIdle = 100;
    private Integer maxActive = 600;
    private Integer timeout = 100000;
}
