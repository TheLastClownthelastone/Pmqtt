package com.pt.mqtt.selfFile;

import java.util.List;

/**
 * @author nate-pt
 * @date 2021/9/28 17:50
 * @Since 1.8
 * @Description 自定义文件 内容格式
 */
public class SelfFileModel {
    /** 版本*/
    private Integer version;
    /** 唯一队列key*/
    private String queueKey;
    /** 消息集合*/
    private List<MessageModel> messageKeys;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getQueueKey() {
        return queueKey;
    }

    public void setQueueKey(String queueKey) {
        this.queueKey = queueKey;
    }

    public List<MessageModel> getMessageKeys() {
        return messageKeys;
    }

    public void setMessageKeys(List<MessageModel> messageKeys) {
        this.messageKeys = messageKeys;
    }
}
