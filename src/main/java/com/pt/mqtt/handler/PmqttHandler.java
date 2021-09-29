package com.pt.mqtt.handler;

import com.pt.mqtt.selfFile.SelfFileHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author nate-pt
 * @date 2021/9/29 15:52
 * @Since 1.8
 * @Description 处理器
 */
public interface PmqttHandler {

    List<SelfFileHandler> selfFiles = new CopyOnWriteArrayList<>();

    /** 业务处理*/
    void handler();

}
