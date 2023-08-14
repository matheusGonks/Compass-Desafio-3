package com.compass.challenge.processingflow.messaging;

import com.compass.challenge.entities.Post;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class PostProducer {

    private final JmsTemplate jmsTemplate;

    public PostProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(Post post) {

        String destination = "MESSAGE_QUEUE";
        jmsTemplate.convertAndSend(destination, post);
    }
}




