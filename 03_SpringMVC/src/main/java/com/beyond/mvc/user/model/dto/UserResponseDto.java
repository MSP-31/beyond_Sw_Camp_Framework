package com.beyond.mvc.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
// @AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDto {
    private final int no;
    private final String name;

    @JsonIgnore
    private final String address;
}
