package org.jmqtt.broker.plugin.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @author yangyong
 */
@Data
@Document(collection = "client_tag")
public class ClientTag {

    @Id
    private String clientId;

    /**
     * app key
     */
    @Field("app_key")
    private String appKey;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 创建时间
     */
    @Field("create_time")
    private Date createTime;
}
