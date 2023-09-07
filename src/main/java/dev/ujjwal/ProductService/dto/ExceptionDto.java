package dev.ujjwal.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;
}
