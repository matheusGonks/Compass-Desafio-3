package com.compass.challenge.external.services;

import com.compass.challenge.entities.Post;

import java.util.List;

public interface PostService {

    void processPost(int id);
    void disablePost(int id);
    void reprocessPost(int id);
    List<Post> findAll();
}
