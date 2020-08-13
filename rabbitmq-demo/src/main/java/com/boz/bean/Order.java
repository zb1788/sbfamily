package com.boz.bean;

import java.io.Serializable;

/**
 * @author boz
 * @date 2019/8/28
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -176025621157830103L;
    private String id;
    private String name;
    private String messageId;

    public Order(){

    }

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}

