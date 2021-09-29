package com.pt.mqtt.handler;

import com.pt.mqtt.selfFile.SelfFileModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nate-pt
 * @date 2021/9/29 16:14
 * @Since 1.8
 * @Description
 */
public abstract class AbstractHandler implements PmqttHandler{
    protected static Map<String, SelfFileModel> selfFileModelMap = new ConcurrentHashMap<>();

}
