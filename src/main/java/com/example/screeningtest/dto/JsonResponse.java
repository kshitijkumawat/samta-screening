package com.example.screeningtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse<T> {

    private boolean success;
    private HttpStatus status;
    private T data;

}
