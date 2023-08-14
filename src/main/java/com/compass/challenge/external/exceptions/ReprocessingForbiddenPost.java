package com.compass.challenge.external.exceptions;

public class ReprocessingForbiddenPost extends RuntimeException{

    public ReprocessingForbiddenPost(){
        super("A Post can only pe Reprocessed if its status is \"ENABLED\" or \"DISABLED\"." );
    }

}
