package com.compass.challenge.external.services;

import com.compass.challenge.entities.Post;
import com.compass.challenge.entities.enums.PostStatus;
import com.compass.challenge.external.exceptions.*;
import com.compass.challenge.processingflow.messaging.PostProducer;
import com.compass.challenge.processingflow.postprocessor.PostProcessor;
import com.compass.challenge.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostProcessor postProcessor;

    private final PostProducer postProducer;

    @Autowired
    PostServiceImpl(PostProducer postProducer, PostRepository postRepository, PostProcessor postProcessor ){
        this.postProducer = postProducer;
        this.postRepository = postRepository;
        this.postProcessor = postProcessor;
    }

    @Override
    public void processPost(int id) {

        if (postRepository.existsById(id)) throw new ProcessingAlreadyExistingPost(id);

        //cria Post
        Post postCreated = new Post(id, LocalDateTime.now());

        //salva no Banco
        postRepository.save(postCreated);

        //manda pra fila da mensageria
        postProducer.sendMessage(postCreated);

    }

    @Override
    public void disablePost(int id) {

        Post postToBeDisabled = postRepository.findById(id).orElseThrow(() -> new NonExistingPostOperation(id));
        if (postToBeDisabled.getCurrentStatus() != PostStatus.ENABLED) throw new DisablingPostNotEnabled();

        postToBeDisabled.updateStatus(PostStatus.DISABLED, LocalDateTime.now());

        //salva no banco
        postRepository.save(postToBeDisabled);

    }

    @Override
    public void reprocessPost(int id){

        Post postToBeReprocessed = postRepository.findById(id).orElseThrow( () -> new NonExistingPostOperation(id) );
        if(postToBeReprocessed.getCurrentStatus() != PostStatus.ENABLED && postToBeReprocessed.getCurrentStatus() != PostStatus.DISABLED){
            throw new ReprocessingForbiddenPost();
        }

        postToBeReprocessed.updateStatus(PostStatus.UPDATING, LocalDateTime.now());
        postRepository.save(postToBeReprocessed);

        postProducer.sendMessage(postToBeReprocessed);

    }

    @Override
    public List<Post> findAll(){
        List<Post> postsProcessed = this.postRepository.findAll();

        if(postsProcessed.isEmpty()) throw new NoPostsProcessed();
        return postsProcessed;
    }
}
