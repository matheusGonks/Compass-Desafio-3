package com.compass.challenge.gateway;

import com.compass.challenge.entities.Comment;
import com.compass.challenge.entities.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalTime.now;

@Component
public class ExternalApiGatewayImpl implements ExternalApiGateway{

    RestTemplate restTemplate;

    ExternalApiGatewayImpl(){
        this.restTemplate = new RestTemplate();
    }

    @Override
    public PayloadAndTimeRequestResponse<Post> getPost(int postId) throws HttpClientErrorException {
        String uri = String.format("https://jsonplaceholder.typicode.com/posts/%d", postId);

        return new PayloadAndTimeRequestResponse(
                restTemplate.getForObject(uri, Post.class),
                LocalDateTime.now());
    }


    public PayloadAndTimeRequestResponse<List<Comment>> getComments(int postId) throws HttpClientErrorException{
        String uri = String.format("https://jsonplaceholder.typicode.com/posts/%d/comments", postId);

        return new PayloadAndTimeRequestResponse(
                Arrays.asList(restTemplate.getForObject(uri, Comment[].class)),
                LocalDateTime.now());
    }

}
