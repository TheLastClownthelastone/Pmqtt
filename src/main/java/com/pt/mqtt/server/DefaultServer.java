package com.pt.mqtt.server;

import com.pt.mqtt.init.InitActuator;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author nate-pt
 * @date 2021/9/29 15:32
 * @Since 1.8
 * @Description
 */
@Data
@AllArgsConstructor
public class DefaultServer implements Server{

    private Class clazz;

    @Override
    public void preStart() {
        InitActuator.init(clazz);
    }

    @Override
    public void start() {
        InitActuator.start();
    }
}
