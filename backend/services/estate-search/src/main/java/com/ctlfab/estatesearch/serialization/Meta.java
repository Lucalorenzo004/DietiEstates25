package com.ctlfab.estatesearch.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Meta {
    private LocalDateTime requestTime;
    private String version;
}
