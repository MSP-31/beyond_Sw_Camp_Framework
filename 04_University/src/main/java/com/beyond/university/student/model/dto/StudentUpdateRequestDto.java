package com.beyond.university.student.model.dto;

import com.beyond.university.student.model.vo.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class StudentUpdateRequestDto {
    private final String no;
    private final String deptNo;
    private final String name;
    private final String address;
    private final String absenceYn;

    public Student toStudent() {
        return Student.builder()
                .no(no)
                .deptNo(deptNo)
                .name(name)
                .address(address)
                .absenceYn(absenceYn)
                .build();
    }
}
