package com.pt.mqtt.selfFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author nate-pt
 * @date 2021/9/28 17:55
 * @Since 1.8
 * @Description 消息模型对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel<T extends Serializable> implements Serializable{
    private static final long serialVersionUID = 3301072683411305787L;
    /** 消息唯一key*/
    private String messageKey;
    /** 消息内容*/
    private T messageContent;
    /** 消费状态*/
    private String consumptionStatus;
}
