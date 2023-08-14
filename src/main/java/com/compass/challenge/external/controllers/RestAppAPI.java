package com.compass.challenge.external.controllers;

import com.compass.challenge.entities.Post;
import com.compass.challenge.external.exceptions.OutOfRangeId;
import com.compass.challenge.external.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class RestAppAPI {

    private final PostService postService;

    @Autowired
    RestAppAPI(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/posts/{postId}")
    public ResponseEntity<String> ProcessPost(@PathVariable int postId){

        if(postId < 0 || postId > 100) throw new OutOfRangeId();
        postService.processPost(postId);

        return new ResponseEntity("Post was submitted.", HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> DisablePost(@PathVariable int postId){
        if(postId < 0 || postId > 100) throw new OutOfRangeId();
        postService.disablePost(postId);

        return new ResponseEntity("Post was disabled.", HttpStatus.ACCEPTED);

    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> ReprocessPost(@PathVariable int postId){
        if(postId < 0 || postId > 100) throw new OutOfRangeId();
        postService.reprocessPost(postId);

        return new ResponseEntity("Post was submitted to reprocessing.", HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts")
    @ResponseBody
    public List<Post> QueryPosts(){
        List<Post> posts = postService.findAll();
        return posts;
    }

}
