package com.beyond.university.department.controller;

import com.beyond.university.common.exception.UniversityException;
import com.beyond.university.common.exception.message.ExceptionMessage;
import com.beyond.university.common.model.dto.BaseResponseDto;
import com.beyond.university.common.model.dto.ItemsResponseDto;
import com.beyond.university.department.model.dto.DepartmentRequestDto;
import com.beyond.university.department.model.service.DepartmentService;
import com.beyond.university.department.model.vo.Department;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/*
    Department APIs

    1) 학과 목록 조회
     - GET /api/v1/university-service/departments

    2) 학과 상세 조회
     - GET /api/v1/university-service/departments/{department-no}

    3) 학과 등록
     - POST /api/v1/university-service/departments

    4) 학과 수정
     - PUT /api/v1/university-service/departments/{department-no}

    5) 학과 삭제
     - DELETE /api/v1/university-service/departments/{department-no}
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/university-service")
@Tag(name = "Department APIs", description = "학과 관련 API 목록")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    @Operation(summary = "학과 목록 조회", description = "전체 학과의 목록을 조회한다.")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호", example = "1"),
            @Parameter(name = "numOfRows", description = "한 페이지 결과 수", example = "10"),
            @Parameter(name = "openYn", description = "개설 여부", example = "Y")
    })
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema = @Schema(implementation = DepartmentsResponseDto.class)
//                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL_SERVER_ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<ItemsResponseDto<Department>> getDepartments(@RequestParam int page,
                                                                       @RequestParam int numOfRows, @RequestParam(required = false) String openYn) {

        int totalCount = departmentService.getTotalCount(openYn);
        List<Department> departments = departmentService.getDepartments(page, numOfRows, openYn);

        if (!departments.isEmpty()) {
            return ResponseEntity.ok(
                    new ItemsResponseDto<>(HttpStatus.OK, departments, page, totalCount)
            );
        } else {
            // JSON 형태로 응답을 보낸 필요가 없을 때 아래와 같이 작성한다.
            // return ResponseEntity.noContent().build();
            // 상태 코드가 204일 때는 본문을 포함할 수 없기 때문에 JSON 형태로 응답이 필요할 때는 아래와 같이 작성한다.
            // return ResponseEntity.ok(
            //         new DepartmentsResponseDto(HttpStatus.NO_CONTENT, departments, page, totalCount)
            // );
            throw new UniversityException(ExceptionMessage.DEPARTMENT_NOT_FOUND);
        }
    }


    @Operation(summary = "학과 상세 조회", description = "학과 번호로 학과의 상세 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL_SERVER_ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/departments/{department-no}")
    public ResponseEntity<BaseResponseDto<Department>> getDepartment(
            @Parameter(description = "학과 번호", example = "001") @PathVariable("department-no") String deptNo) {
        Department department = departmentService.getDepartmentByNo(deptNo)
                .orElseThrow(() -> new UniversityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));

        return ResponseEntity.ok(new BaseResponseDto<>(HttpStatus.OK, department));
    }

    @PostMapping("/departments")
    public ResponseEntity<BaseResponseDto<Department>> getDepartments(@RequestBody DepartmentRequestDto requestDto) {
        Department department = requestDto.toDepartment();

        System.out.println(department);

        departmentService.save(department);

        return ResponseEntity.created(URI.create("/departments/"+ department.getNo())).build();
    }
}