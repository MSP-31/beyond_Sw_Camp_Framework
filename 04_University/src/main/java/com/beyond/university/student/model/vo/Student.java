package com.beyond.university.student.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String no;

    private String deptNo;

    private String name;

    private String ssn;

    private String address;

    private LocalDate entranceDate;

    private String absenceYn;

    private String coachProfessorNo;
}