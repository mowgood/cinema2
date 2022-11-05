package com.cinema.web.controller;

public class BoardForm {

    private String idx;
    private String title;
    private String contents;
    private String crea_id;

    public String getIdx() {
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getCrea_Id() {
        return crea_id;
    }
    public void setCrea_id(String crea_id) {
        this.crea_id = crea_id;
    }
}
