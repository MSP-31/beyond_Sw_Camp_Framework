package com.beyond.university.common.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiErrorResponseDto {
    private int value;
    private String type;
    private String message;

    public ApiErrorResponseDto(int value, String type, String message) {
        this.value = value;
        this.type = type;
        this.message = message;
    }
}
