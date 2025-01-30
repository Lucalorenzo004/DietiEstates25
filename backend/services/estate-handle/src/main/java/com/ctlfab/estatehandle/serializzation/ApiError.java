package com.ctlfab.estatehandle.serializzation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String field;
    private String message;
}
