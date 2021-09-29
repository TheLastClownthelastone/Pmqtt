package com.pt.mqtt.selfFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author nate-pt
 * @date 2021/9/28 17:50
 * @Since 1.8
 * @Description 自定义文件 内容格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SelfFileModel implements Serializable {
    private static final long serialVersionUID = 1784217583289285562L;
    /** 版本*/
    private Integer version;
    /** 唯一队列key*/
    private String queueKey;
    /** 消息集合*/
    private List<MessageModel> messageKeys;

}
