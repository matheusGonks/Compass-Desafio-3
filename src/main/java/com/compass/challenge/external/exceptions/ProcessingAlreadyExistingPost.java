package com.compass.challenge.external.exceptions;

public class ProcessingAlreadyExistingPost extends RuntimeException{

    public ProcessingAlreadyExistingPost(int id){
        super(String.format("The %d id is related to an already existing Post, therefore it cant be Processed.",id));
    }
}
