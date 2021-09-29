package com.pt.mqtt.selfFile;

import java.io.Serializable;

/**
 * @author nate-pt
 * @date 2021/9/28 17:55
 * @Since 1.8
 * @Description 消息模型对象
 */
public class MessageModel<T extends Serializable> {
    /** 消息唯一key*/
    private String messageKey;
    /** 消息内容*/
    private T messageContent;
    /** 消费状态*/
    private String consumptionStatus;

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public T getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(T messageContent) {
        this.messageContent = messageContent;
    }

    public String getConsumptionStatus() {
        return consumptionStatus;
    }

    public void setConsumptionStatus(String consumptionStatus) {
        this.consumptionStatus = consumptionStatus;
    }
}
