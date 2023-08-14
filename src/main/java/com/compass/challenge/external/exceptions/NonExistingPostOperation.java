package com.compass.challenge.external.exceptions;

public class NonExistingPostOperation extends RuntimeException {

    public NonExistingPostOperation(int id){
        super(String.format("A post with id = %d does not exist.", id));
    }
}
