package com.compass.challenge.entities;

import jakarta.persistence.*;

@Embeddable
public class Comment {

    private int id;
    private String body;

    Comment(){}

    public Comment(int id, String body){
        this.id = id;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }
}
