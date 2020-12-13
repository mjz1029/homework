/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.messageboard.javabean;

/**
 *
 * @author yangxuan
 */
public class Message {
    private long mid;
    private String title;
    private String content;
    private String userid;

    public Message() {
    }

    public Message(long mid, String title, String content, String userid) {
        this.mid = mid;
        this.title = title;
        this.content = content;
        this.userid = userid;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
