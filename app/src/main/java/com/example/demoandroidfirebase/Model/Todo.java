package com.example.demoandroidfirebase.Model;

import java.util.HashMap;

public class Todo {
    private String id, title, content;
    private boolean checkStatus;

    public Todo(String id, String title, String content, boolean checkStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.checkStatus = checkStatus;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public HashMap<String,Object> convertHashMap()
    {
        HashMap<String,Object> work=new HashMap<>();
        work.put("id",id);
        work.put("title",title);
        work.put("content",content);
        work.put("checkStatus", checkStatus);
        return work;
    }
}
