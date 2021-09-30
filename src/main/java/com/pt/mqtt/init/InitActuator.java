package com.pt.mqtt.init;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReferenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.pt.mqtt.anno.Handler;
import com.pt.mqtt.config.MqttContent;
import com.pt.mqtt.handler.PmqttHandler;
import com.pt.mqtt.server.MqttServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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

    /**
     * 服务启动
     */
    public static void start(){
        // 链接线程池设置1个线程组
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        // 工作线程设置cpu内核数两倍
        NioEventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2);

        try {
            // 服务器启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 获取管道
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new MqttServerHandler());
                        }
                    });
            // 异步进行端口绑定
            ChannelFuture sync = serverBootstrap.bind(MqttContent.MQTTPORT).sync();
            // 使用监听器，监听端口是否异步绑定成功
            sync.addListener((future)->{
                if (future.isSuccess()) {
                    log.info("【服务器启动成功】： 端口......{}............",MqttContent.MQTTPORT);
                }
            });
            // 异步进行端口关闭
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }

}
