package com.beyond.mvc.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@ToString
// @NoArgsConstructor
// @AllArgsConstructor
@RequiredArgsConstructor
public class LoginRequestDto {
    private final String username;
    private final String password;
}
