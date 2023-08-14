package com.compass.challenge.external.exceptions;

public class DisablingPostNotEnabled extends RuntimeException{

    public DisablingPostNotEnabled(){
        super("A post can only be disabled if its status is \"ENABLED\".");
    }
}
