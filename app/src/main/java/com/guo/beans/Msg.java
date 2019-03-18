package com.guo.beans;

/**
 * Created by guo_w on 2019/3/18.
 */

public class Msg {
    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private TYPE type;

    public enum TYPE{
        RECEIVED,
        SENT
    }


    public Msg() {
    }

    public Msg(String content, TYPE type){
        this.content = content;
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}