package com.compass.challenge.entities.enums;

public enum PostStatus {
    CREATED("CREATED"),
    POST_FIND("POST_FIND"),
    POST_OK("POST_OK"),
    COMMENTS_FIND("COMMENTS_FIND"),
    COMMENTS_OK("COMMENTS_OK"),
    FAILED("FAILED"),
    ENABLED("ENABLED"),
    DISABLED("DISABLED"),
    UPDATING("UPDATING");

    String status;

    PostStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
