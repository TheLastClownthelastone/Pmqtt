package com.pt.mqtt.file;

import com.pt.mqtt.selfFile.MessageModel;
import com.pt.mqtt.selfFile.SelfFileHandler;
import com.pt.mqtt.selfFile.SelfFileModel;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author nate-pt
 * @date 2021/9/28 16:57
 * @Since 1.8
 * @Description 自定义文件
 */
public class SelfFileTest {

    /**
     * 写入自定义文件
     * @throws Exception
     */
    @Test
    public void exec1() throws Exception{
        //
        File file = new File("D:\\myworkspace\\Pmqtt\\pt\\test.pt");
        FF ff = new FF();
        ff.setHead("head");
        ff.setBody("body");
        OutputStream os = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(ff);

        os.close();
        objectOutputStream.close();
    }

    @Test
    public void exec2() throws Exception{
        File file = new File("pt/test.pt");
        InputStream fileInputStream = new FileInputStream(file);

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        FF ff = (FF) objectInputStream.readObject();
        System.out.println(ff);

        ff.setBody(ff.getBody()+"进行了修改");
        OutputStream os = new FileOutputStream(file);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        objectOutputStream.writeObject(ff);

        objectInputStream.close();
        objectOutputStream.close();
        fileInputStream.close();
        os.close();
    }


    @Test
    public void exec3() throws InterruptedException {
        SelfFileHandler selfFileHandler = new SelfFileHandler();
        CountDownLatch countDownLatch  = new CountDownLatch(15);
        for (int i = 0; i < 15; i++) {
            String s = String.valueOf(i);
            new Thread(()->{
                selfFileHandler.saveFile(_build_model("testQueue"));
                countDownLatch.countDown();
            },s).start();
        }
        countDownLatch.await();
        System.out.println("所有线程任务执行完毕.................");
    }


    @Test
    public void exec4(){
        SelfFileHandler selfFileHandler = new SelfFileHandler();
        SelfFileModel testQueue = selfFileHandler.parse("testQueue");
        System.out.println(testQueue);
    }

    private SelfFileModel _build_model(String queueName){
        SelfFileModel model = new SelfFileModel();
        model.setQueueKey(queueName);
        model.setVersion(1);
        List<MessageModel> list = List.of(new MessageModel("1", "test", "1"),
                new MessageModel("2", "test2", "1"),
                new MessageModel("3", "test3", "3"));
        model.setMessageKeys(list);
        return model;
    }



}
