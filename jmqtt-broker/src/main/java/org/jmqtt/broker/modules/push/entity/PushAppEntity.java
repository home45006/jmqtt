package org.jmqtt.broker.modules.push.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用信息
 *
 * @author yangyong
 * @email p_pyongyang@tencent.com
 * @date 2019-06-27 13:53:45
 */
@Data
@TableName("push_app")
public class PushAppEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用简介
     */
    private String appDescription;
    /**
     * 应用平台
     */
    private String platformTypes;
    /**
     * 应用包名
     */
    private String appPkg;
    /**
     * AppKey
     */
    private String appKey;
    /**
     * Master Secret
     */
    private String masterSecret;
    /**
     * 预留字段
     */
    private String temp1;
    /**
     * 预留字段
     */
    private String temp2;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 修改者
     */
    private String updateBy;

}
