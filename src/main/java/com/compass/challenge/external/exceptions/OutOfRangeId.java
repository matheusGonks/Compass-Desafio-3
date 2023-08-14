package com.compass.challenge.external.exceptions;

public class OutOfRangeId extends RuntimeException{

    public OutOfRangeId(){
        super("Id provided violates constraints. Id must be >= 100 and <= 1.");
    }

}
