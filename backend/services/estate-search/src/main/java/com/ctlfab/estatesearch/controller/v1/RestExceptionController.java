package com.ctlfab.estatesearch.controller.v1;

import com.ctlfab.estatesearch.exceptions.AppException;
import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class RestExceptionController {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ApiResponse<AppException>> handleException(AppException exception){
        Meta meta = new Meta(now(), "v1");

        String status = "Error";
        ApiResponse<AppException> response = new ApiResponse<>(status, exception, meta);

        return new ResponseEntity<>(response, exception.getStatus());
    }
}
