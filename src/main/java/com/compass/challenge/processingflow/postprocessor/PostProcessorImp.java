package com.compass.challenge.processingflow.postprocessor;

import com.compass.challenge.entities.Comment;
import com.compass.challenge.entities.Post;
import com.compass.challenge.entities.enums.PostStatus;
import com.compass.challenge.gateway.ExternalApiGateway;
import com.compass.challenge.gateway.PayloadAndTimeRequestResponse;
import com.compass.challenge.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostProcessorImp implements PostProcessor {

    ExternalApiGateway externalApiGateway;
    PostRepository postRepository;;

    @Autowired
    PostProcessorImp(ExternalApiGateway externalApiGateway, PostRepository postRepository){
        this.externalApiGateway = externalApiGateway;
        this.postRepository = postRepository;
    }


    //pega post da mensageria e busca -> atualiza para Post Find
    public void postFind(Post postInProcessing) {

        try {
            postInProcessing.updateStatus(PostStatus.POST_FIND, LocalDateTime.now());
            this.postRepository.save(postInProcessing);

            PayloadAndTimeRequestResponse<Post> paylodAndTime = externalApiGateway.getPost(postInProcessing.getId());
            Post retrievedPost = paylodAndTime.getPayload();

            this.postOk(postInProcessing, retrievedPost, paylodAndTime.getTime());

        } catch (HttpClientErrorException errorException) {

            this.postFailed(postInProcessing);
        }
    }
    public void postOk(Post postInProcessing, Post retrievedPost, LocalDateTime retrievedTime){

        postInProcessing.setBody(retrievedPost.getBody());
        postInProcessing.setTitle(retrievedPost.getTitle());

        postInProcessing.updateStatus(PostStatus.POST_OK, retrievedTime);
        this.postRepository.save(postInProcessing);

        this.commentsFind(postInProcessing);

    }

    public void commentsFind(Post postInProcessing){

        try {
            postInProcessing.updateStatus(PostStatus.COMMENTS_FIND, LocalDateTime.now());
            this.postRepository.save(postInProcessing);

            PayloadAndTimeRequestResponse<List<Comment>> payloadAndTime = externalApiGateway.getComments(postInProcessing.getId());
            List<Comment> retrievedComments = payloadAndTime.getPayload();

            this.commentsOk(postInProcessing, retrievedComments, payloadAndTime.getTime());

        } catch (HttpClientErrorException errorException){

            this.postFailed(postInProcessing);
        }
    }

    public void commentsOk(Post postInProcessing, List<Comment> retrievedComments ,LocalDateTime retrievedTime){

        postInProcessing.setComments(retrievedComments);
        postInProcessing.updateStatus(PostStatus.COMMENTS_OK, retrievedTime);
        this.postRepository.save(postInProcessing);

        this.enablePost(postInProcessing);
    }

    public void enablePost(Post postInProcessing){

        postInProcessing.updateStatus(PostStatus.ENABLED, LocalDateTime.now());
        this.postRepository.save(postInProcessing);

    }

    public void postFailed(Post postInProcessing){
        postInProcessing.updateStatus(PostStatus.FAILED, LocalDateTime.now());
        this.postRepository.save(postInProcessing);

        this.postDisable(postInProcessing);
    }

    public void postDisable(Post postInProcessing){
        postInProcessing.updateStatus(PostStatus.DISABLED, LocalDateTime.now());
        this.postRepository.save(postInProcessing);
    }

}
