package com.pt.mqtt.Init;

import com.pt.mqtt.BootStrap;
import com.pt.mqtt.init.InitActuator;
import com.pt.mqtt.selfFile.SelfFileHandler;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nate-pt
 * @date 2021/9/29 16:56
 * @Since 1.8
 * @Description 初始化测试
 */
public class InitTest {

    @Test
    public  void exec1(){
        InitActuator.init(BootStrap.class);
        SelfFileHandler selfFileHandler = new SelfFileHandler();
        System.out.println(selfFileHandler.getSelfModelMap());
    }

    @Test
    public void exec2(){
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("1",null);
        System.out.println(hashMap);

        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("1",null);
        System.out.println(map);
    }

}
