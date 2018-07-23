package com.example.application.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */


public class DataBean {
    /**
     * id : 1
     * image : http://p1.meituan.net/movie/20803f59291c47e1e116c11963ce019e68711.jpg@160w_220h_1e_1c
     * title : 霸王别姬
     * actor : 张国荣,张丰毅,巩俐
     * createtime : 1993-01-01(中国香港)
     * score : 9.6
     */

    private int id;
    private String image;
    private String title;
    private String actor;
    private String createtime;
    private String score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
