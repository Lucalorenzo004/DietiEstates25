package com.ctlfab.estatehandle.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private LocalDateTime timestamp;
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private Map<?, ?> data;
}