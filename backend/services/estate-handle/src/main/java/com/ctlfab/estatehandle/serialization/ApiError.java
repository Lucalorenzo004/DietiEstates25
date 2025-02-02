package com.ctlfab.estatehandle.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String field;
    private String message;
}
