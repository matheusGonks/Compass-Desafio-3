package com.compass.challenge.external.exceptions;

public class NoPostsProcessed extends RuntimeException{

    public NoPostsProcessed(){
        super("There are no processed posts yet.");
    }
}
