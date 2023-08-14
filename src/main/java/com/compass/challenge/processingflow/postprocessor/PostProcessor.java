package com.compass.challenge.processingflow.postprocessor;

import com.compass.challenge.entities.Comment;
import com.compass.challenge.entities.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostProcessor {

    public void postFind(Post postInProcessing);
    public void postOk(Post postInProcessing, Post retrievedPost, LocalDateTime retrievedTime);
    public void commentsFind(Post postInProcessing);
    public void commentsOk(Post postInProcessing, List<Comment> retrievedComments , LocalDateTime retrievedTime);
    public void enablePost(Post postInProcessing);


}
