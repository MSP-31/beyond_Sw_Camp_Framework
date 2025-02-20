package com.beyond.university.auth.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int no;

    private String id;

    private String password;

    private String role;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String hobby;

    private String status;

    private LocalDate enrollDate;

    private LocalDate modifyDate;
}