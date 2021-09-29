package com.pt.mqtt.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

/**
 * @author nate-pt
 * @date 2021/9/28 16:22
 * @Since 1.8
 * @Description 全局配置
 */
public class MqttConfig {

    /** 配置文件路径*/
    private static final String _SYSTEM_CONFIG_FILE = "config/myself.conf";
    /** 全局配置文件*/
    private static  Config config = null;
    static {
        // 总配置
        var a = ConfigFactory.parseFile(new File(_SYSTEM_CONFIG_FILE));
        var load = ConfigFactory.load(a);
        config = load;
    }

    /** 获取配置信息*/
    public static Config getConfig(){
        return config;
    }
}
