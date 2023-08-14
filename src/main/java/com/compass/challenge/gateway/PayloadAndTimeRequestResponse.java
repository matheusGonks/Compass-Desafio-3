package com.compass.challenge.gateway;

import com.compass.challenge.entities.Post;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class PayloadAndTimeRequestResponse<T> {

    T payload;
    LocalDateTime time;


    PayloadAndTimeRequestResponse(T payload, LocalDateTime time){
        this.payload = payload;
        this.time = time;
    }

    public T getPayload(){
        return this.payload;
    };

    public LocalDateTime getTime(){
        return this.time;
    };

}