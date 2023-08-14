package com.compass.challenge.external.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
class CustomExceptionHandler {


    @ExceptionHandler(ProcessingAlreadyExistingPost.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String ProcessingAlreadyExistingPostHandler(ProcessingAlreadyExistingPost ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ReprocessingForbiddenPost.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String ReprocessingForbiddenPostHandler(ReprocessingForbiddenPost ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NonExistingPostOperation.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String NonExistingPostOperationHandler(NonExistingPostOperation ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DisablingPostNotEnabled.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String DisablingPostNotEnabledHandler(DisablingPostNotEnabled ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoPostsProcessed.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String NoPostsProcessedHandler(NoPostsProcessed ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(OutOfRangeId.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String OutOfRangeIdHandler(OutOfRangeId ex) {
        return ex.getMessage();
    }
}
