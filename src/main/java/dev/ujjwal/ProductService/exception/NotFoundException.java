package dev.ujjwal.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) --> if we only want to return the status code when a particular exception is thrown
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
