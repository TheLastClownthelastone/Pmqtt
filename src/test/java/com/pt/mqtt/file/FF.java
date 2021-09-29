package com.pt.mqtt.file;

import java.io.Serializable;

/**
 * @author nate-pt
 * @date 2021/9/28 17:37
 * @Since 1.8
 * @Description
 */
public class FF implements Serializable {
    private static final long serialVersionUID = 1016387841192211188L;

    private String head;
    private String body;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FF{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
