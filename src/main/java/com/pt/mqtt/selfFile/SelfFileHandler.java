package com.pt.mqtt.selfFile;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nate-pt
 * @date 2021/9/28 17:58
 * @Since 1.8
 * @Description 自定义文件处理器
 */
@Slf4j
public class SelfFileHandler {
    /**
     * 全局根目录地址
     */
    private static final String _ROOT_FILE_PATH = "pt";
    /**
     * 自定义文件后缀名
     */
    private static final String _FILE_PREFIX = ".pt";
    /**
     * 数据写入的时候的写锁
     */
    private static final Map<String, AtomicBoolean> _WRITE_LOCK = new ConcurrentHashMap<>();

    /**
     * 防止重复创建文件锁
     */
    private static final ReentrantLock _LOCK = new ReentrantLock();


    /**
     * 数据存入自定义文件中
     *
     * @param selfFileModel 文件写入操作进行上锁操作
     */
    public void saveFile(SelfFileModel selfFileModel) {
        AtomicBoolean atomicBoolean = _WRITE_LOCK.get(selfFileModel.getQueueKey());
        OutputStream out = null;
        ObjectOutputStream obs = null;
        try {
            // 第一次新增队列的时候
            if (atomicBoolean == null) {
                log.info("【线程：{}】 创建自定义文件....",Thread.currentThread().getName());
                // 防止多线程操作情况加上锁
                _LOCK.lock();
                AtomicBoolean aBoolean = _WRITE_LOCK.get(selfFileModel.getQueueKey());
                try {
                    log.info("多线程执并行中。。。，当前执行线程为：{}",Thread.currentThread().getName());
                    if (aBoolean == null) {
                        File file = new File(_ROOT_FILE_PATH + File.separator + selfFileModel.getQueueKey() + _FILE_PREFIX);
                        AtomicBoolean aBoolean1 = new AtomicBoolean(false);
                        out = new FileOutputStream(file);
                        obs = new ObjectOutputStream(out);
                        obs.writeObject(selfFileModel);
                        _WRITE_LOCK.put(selfFileModel.getQueueKey(), aBoolean1);
                    }
                } finally {
                    _LOCK.unlock();
                }
            } else {
                log.info("文件存在执行修改操作。。。");
                broke:
                {
                    // 第二次开始之后，防止并发操作
                    if (atomicBoolean.compareAndSet(false, true)) {
                        log.info("当前执行文件修改的线程为：",Thread.currentThread().getName());
                        File file = new File(_ROOT_FILE_PATH + File.separator + selfFileModel.getQueueKey() + _FILE_PREFIX);
                        AtomicBoolean aBoolean1 = new AtomicBoolean(false);
                        out = new FileOutputStream(file);
                        obs = new ObjectOutputStream(out);
                        obs.writeObject(selfFileModel);
                    } else {
                        log.info("等待修改的线程为：{}.....",Thread.currentThread().getName());
                        // 另外线程继续调回断点
                        break broke;
                    }
                }
            }
        } catch (Exception e) {
            log.error("【{}】：数据录入失败。。，时间：{}", selfFileModel.getQueueKey() + _FILE_PREFIX, DateUtil.formatDate(new Date()));
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(obs);
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 解析自定义文件
     * @param queueKey
     * @return
     */
    public SelfFileModel parse(String queueKey){
        String fileName = _ROOT_FILE_PATH+File.separator+queueKey+_FILE_PREFIX;
        InputStream in = null;
        ObjectInputStream oin = null;
        try {
            File file = new File(fileName);
            in = new FileInputStream(file);
            oin = new ObjectInputStream(oin);
            return (SelfFileModel) oin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(oin);
        }
        return null;
    }


}
