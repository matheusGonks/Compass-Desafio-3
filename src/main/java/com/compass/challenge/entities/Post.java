package com.compass.challenge.entities;

import com.compass.challenge.entities.enums.PostStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @JsonProperty(index = 1)
    @Id
    private int id;

    @JsonProperty(index = 2)
    private String title;

    @JsonProperty(index = 3)
    private String body;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_comments", joinColumns = @JoinColumn(name = "post_id"))
    private List<Comment> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post_history", joinColumns = @JoinColumn(name = "post_id"))
    private List<History> history;

    public Post(){}

    public Post(int id, LocalDateTime creationTime) {
        this.id = id;
        this.title = "";
        this.body = "";
        this.comments = new ArrayList<Comment>();
        this.history = new ArrayList<History>();

        this.history.add(new History(PostStatus.CREATED, creationTime.toString()));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addHistory(History newHistory){
        this.history.add(newHistory);
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
    }

    public List<Comment> getComments(){
        return this.comments;
    }

    public List<History> getHistory() {
        return history;
    }

    public void updateStatus(PostStatus newStatus, LocalDateTime statusUpdateTime){
        History newStatusAddedToHistory = new History(newStatus, statusUpdateTime.toString());
        this.addHistory(newStatusAddedToHistory);
    }

    public PostStatus getCurrentStatus(){
        return this.history.get(history.size() - 1).getStatus();
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", comments=" + comments +
                ", history=" + history +
                '}';
    }
}