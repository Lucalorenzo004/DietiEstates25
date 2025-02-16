package com.ctlfab.estatehandle.serialization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    private LocalDateTime requestTime;
    private String version;
}
