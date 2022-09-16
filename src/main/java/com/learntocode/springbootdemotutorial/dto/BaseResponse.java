package com.learntocode.springbootdemotutorial.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private HttpStatus status;
    private String message;
}
