package com.vcg.community.mapping;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 摄影社区关键词
 * @author lei.fang
 * @since 2016/12/29
 */
public class Keyword implements Serializable{


    //关键词
    private String keyword;

    //关键词权重
    private double score;

    public Keyword() {
    }

    public Keyword(String keyword, double score) {
        this.keyword = keyword;
        this.score = score;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
