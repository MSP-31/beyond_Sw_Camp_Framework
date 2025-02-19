package com.beyond.university.student.model.dto;

import com.beyond.university.student.model.vo.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class StudentRegisterRequestDto {
    private final String deptNo;

    private final String name;

    private final String ssn;

    private final String address;

    public Student toStudent() {
        return Student.builder()
                .deptNo(deptNo)
                .name(name)
                .ssn(ssn)
                .address(address)
                .build();
    }
}
