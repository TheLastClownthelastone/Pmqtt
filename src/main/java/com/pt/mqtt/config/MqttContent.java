package com.pt.mqtt.config;

/**
 * @author nate-pt
 * @date 2021/9/30 9:38
 * @Since 1.8
 * @Description 获取Mqtt 的常量
 */
public class MqttContent {
    /** MQTT 服务器端口*/
    public static final int MQTTPORT = MqttConfig.getConfig().getInt("pmqtt.port");
}
