package com.beyond.university.common.exception.handler;

import com.beyond.university.common.exception.UniversityException;
import com.beyond.university.common.exception.dto.ApiErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UniversityException.class)
    public ResponseEntity<Object> handleException(UniversityException e) {
        // Map<String, Object> response = new HashMap<>();

        log.error("UniversityException : {}",e.getMessage());

        // response.put("code", e.getStatus().value());
        // response.put("status", e.getStatus().name());
        // response.put("message", e.getMessage());

        // return new ResponseEntity<>(response, e.getStatus());
        return new ResponseEntity<>(
                new ApiErrorResponseDto(e.getStatus().value(),e.getType(),e.getMessage()),
                e.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(Exception e) {
        // Map<String, Object> response = new HashMap<>();
        log.error("Global Exception : {}", e.getMessage());

        // response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        // response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.name());
        // response.put("message", e.getMessage());

        return new ResponseEntity<>(
                new ApiErrorResponseDto(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.name(),
                        e.getMessage())
                , HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
