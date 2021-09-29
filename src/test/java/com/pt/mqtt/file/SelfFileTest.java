package com.pt.mqtt.file;

import org.junit.Test;

import java.io.*;

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






}
