
package com.backend.user.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;
    private T data;
    private Meta meta;
    private List<ApiError> errors;

    public ApiResponse(String status, T data, Meta meta){
        this.status = status;
        this.data = data;
        this.meta = meta;
    }

    public ApiResponse(String status, Meta meta){
        this.status = status;
        this.meta = meta;
    }

    public ApiResponse(String status, T data, Meta meta, List<ApiError> errors){
        this.status = status;
        this.data = data;
        this.meta = meta;
        this.errors = errors;
    }
}
