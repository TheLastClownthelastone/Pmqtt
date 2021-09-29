package com.pt.mqtt.selfFile;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author nate-pt
 * @date 2021/9/28 17:58
 * @Since 1.8
 * @Description 自定义文件处理器
 */
@Slf4j
public class SelfFileHandler {
    /** 全局根目录地址*/
    private static final String _ROOT_FILE_PATH = "pt";
    /** 自定义文件后缀名*/
    private static final String _FILE_PREFIX = ".pt";
    /** 数据写入的时候的写锁*/
    private static final Map<String,AtomicBoolean> _WRITE_LOCK= new ConcurrentHashMap<>();


    /**
     * 数据存入自定义文件中
     * @param selfFileModel
     * 文件写入操作进行上锁操作
     */
    public void saveFile(SelfFileModel selfFileModel){
        AtomicBoolean atomicBoolean = _WRITE_LOCK.get(selfFileModel.getQueueKey());
        //
        if (atomicBoolean == null) {
            /** 队列名称创建对应的文件*/
            String fileName = _ROOT_FILE_PATH+ File.separator+selfFileModel.getQueueKey()+_FILE_PREFIX;
            File file = new File(fileName);
        }


    }
}
