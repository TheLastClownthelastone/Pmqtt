package com.pt.mqtt.server;

import com.pt.mqtt.BootStrap;
import org.junit.Test;

/**
 * @author nate-pt
 * @date 2021/9/30 9:52
 * @Since 1.8
 * @Description
 */
public class ServerStartTest {

    @Test
    public void exec1(){
        Server  server = new DefaultServer(BootStrap.class);
        server.start();
    }
}
