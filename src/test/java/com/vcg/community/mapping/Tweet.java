package com.vcg.community.mapping;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @auhthor lei.fang@shijue.me
 * @since . 2017-07-07
 */
@Document(indexName = "twitter",type = "tweet")
public class Tweet {

    private String id;
    private String user;
    private Date postDate;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
