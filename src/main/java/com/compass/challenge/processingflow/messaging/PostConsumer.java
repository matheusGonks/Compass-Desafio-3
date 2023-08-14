package com.compass.challenge.processingflow.messaging;

import com.compass.challenge.entities.Comment;
import com.compass.challenge.entities.Post;
import com.compass.challenge.processingflow.postprocessor.PostProcessor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PostConsumer {

    private final PostProcessor postProcessor;

    PostConsumer(PostProcessor postProcessor){
        this.postProcessor = postProcessor;
    }

    @JmsListener(destination = "MESSAGE_QUEUE", containerFactory = "myFactory")
    public void receiveMessage(Post post) {
        System.out.println("Received <" + post + ">");
        postProcessor.postFind(post);
    }

}