package com.pt.mqtt.init;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReferenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.pt.mqtt.anno.Handler;
import com.pt.mqtt.handler.PmqttHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author nate-pt
 * @date 2021/9/29 15:14
 * @Since 1.8
 * @Description 初始化执行器
 */
@Slf4j
public class InitActuator {

    private static final AtomicBoolean _CHECK = new AtomicBoolean(false);


    /**
     * 初始化方法
     */
    public static void init(Class clazz){
        if (_CHECK.compareAndSet(false,true)) {
            String name = clazz.getName();
            String scanPacketPath = name.substring(0,name.lastIndexOf("."));
            // 加载handler,必须是实现了PmqttHandler接口的
            Set<Class<?>> classes = ClassScanner.scanPackageBySuper(scanPacketPath, PmqttHandler.class);
            // 并且带有@Handler注解
            classes.stream().filter(c->c.isAnnotationPresent(Handler.class)).forEach(c->{
                PmqttHandler handler = (PmqttHandler) ReflectUtil.newInstance(c);
                handler.handler();
            });
        }else {
            log.warn("【程勋初始化方法只能执行一次】..............");
            return;
        }
    }

}
