package com.compass.challenge.entities;

import com.compass.challenge.entities.enums.PostStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Embeddable
public class History {

    @Temporal(TemporalType.TIMESTAMP)
    private String date;

    private PostStatus status;

    public History(){}

    public History(PostStatus status, String creationDate){
        this.date = creationDate;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "History{" +
                "date=" + date +
                ", status=" + status +
                '}';
    }
}

