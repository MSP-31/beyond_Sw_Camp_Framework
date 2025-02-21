package com.beyond.university.department.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String no;

    private String name;

    private String category;

    private String openYn;

    private int capacity;
}