package com.pt.mqtt.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

import java.io.File;

/**
 * @author nate-pt
 * @date 2021/9/28 15:33
 * @Since 1.8
 * @Description 配置相关测试
 */
public class ConfigTest {

    @Test
    public void exec1(){
        // 创建需要被加载的配置文件
        var config = ConfigFactory.parseFile(new File("D:\\myworkspace\\Pmqtt\\config\\myself.conf"));
        var load = ConfigFactory.load(config);
        System.out.println(load.getString("lobbyserver.port"));
        String html = """
              <html>
                  <body>
                      <p>Hello, world</p>
                  </body>
              </html>
              """;
        System.out.println(html);
    }


    @Test
    public void exec2(){
        System.out.println(MqttConfig.getConfig().getInt("lobbyserver.port"));
    }



}
