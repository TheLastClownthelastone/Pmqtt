package com.pt.mqtt.server;

/**
 * @author nate-pt
 * @date 2021/9/29 15:27
 * @Since 1.8
 * @Description 统一接口
 */
public interface Server {

    /**
     * 预处理
     */
    void preStart();

    /**
     * 服务启动
     */
    void  start();

}
