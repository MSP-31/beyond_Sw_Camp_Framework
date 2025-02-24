package com.beyond.university.department.model.dto;

import com.beyond.university.department.model.vo.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class DepartmentRequestDto {
    @Schema(description = "학과 이름", example = "일어 학과")
    private  final String name;

    @Schema(description = "계열", example = "인문사회")
    private final String category;

    @Schema(description = "개설 여부", example = "Y")
    private final String openYn;

    @Schema(description = "정원", example = "50")
    private final int capacity;

    public Department toDepartment() {
        return Department.builder()
                .name(name)
                .capacity(capacity)
                .openYn(openYn)
                .capacity(capacity)
                .build();
    }
}
