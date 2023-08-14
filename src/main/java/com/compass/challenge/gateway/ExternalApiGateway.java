package com.compass.challenge.gateway;

import com.compass.challenge.entities.Comment;
import com.compass.challenge.entities.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExternalApiGateway {

    PayloadAndTimeRequestResponse<Post> getPost(int postId);
    PayloadAndTimeRequestResponse<List<Comment>> getComments(int postId);


}
