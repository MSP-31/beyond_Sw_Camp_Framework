package com.beyond.university.student.controller;

import com.beyond.university.department.model.dto.DepartmentsDto;
import com.beyond.university.department.model.service.DepartmentService;
import com.beyond.university.department.model.vo.Department;
import com.beyond.university.student.model.dto.StudentRegisterRequestDto;
import com.beyond.university.student.model.dto.StudentUpdateRequestDto;
import com.beyond.university.student.model.dto.StudentsDto;
import com.beyond.university.student.model.service.StudentService;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final DepartmentService departmentService;

    @GetMapping("/search")
    public ModelAndView search(ModelAndView modelAndView,
                               @RequestParam(required = false) String deptNo) {
        List<DepartmentsDto> departments =
            departmentService.getDeartments()
                            .stream()
                            .map(DepartmentsDto::new)
                            .toList();

        log.info("departments.size() : {}", departments.size());

        if (deptNo != null){
            List<StudentsDto> students =
                    studentService.getStudentsByDeptNo(deptNo)
                            .stream()
                            .map(StudentsDto::new)
                            .toList();

            log.info("students.size() : {}", students.size());

            modelAndView.addObject("students", students);
        }

        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("student/search");

        return modelAndView;
    }

    @GetMapping("/info")
    public ModelAndView info(ModelAndView modelAndView, @RequestParam String sno) {
        Student student = studentService.getStudentsByNo(sno);

        List<DepartmentsDto> departments =
                departmentService.getDeartments()
                        .stream()
                        .map(DepartmentsDto::new)
                        .toList();

        // 연관 관계 매핑 테스트
        // System.out.println(student);
        // System.out.println(student.getDepartment());
        Department department = departmentService.getDepartmentByNo(student.getDeptNo());

        System.out.println(department);

        for (Student s : department.getStudents()) {
            System.out.println(s);
        }

        modelAndView.addObject("student", student);
        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("student/info");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        List<DepartmentsDto> departments =
                departmentService.getDeartments()
                        .stream()
                        .map(DepartmentsDto::new)
                        .toList();

        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("student/register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(ModelAndView modelAndView, StudentRegisterRequestDto requestDto) {
        int result = 0;
        Student student = requestDto.toStudent();

        result = studentService.save(student);

        log.info("student no : {}", student.getNo());

        if (result > 0) {
            modelAndView.addObject("msg","학생이 등록되었습니다.");
            modelAndView.addObject("location","/student/info?sno=" + student.getNo());
        } else {
            modelAndView.addObject("msg","학생 등록을 실패하였습니다.");
            modelAndView.addObject("location","/student/register");
        }

        modelAndView.setViewName("common/msg");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(ModelAndView modelAndView, StudentUpdateRequestDto requestDto){
        int result = 0;
        Student student = requestDto.toStudent();

        System.out.println(student);

        result = studentService.save(student);

        if (result > 0) {
            modelAndView.addObject("msg","학생 정보가 수정되었습니다.");
        } else {
            modelAndView.addObject("msg","학생 정보 수정을 실패하였습니다.");
        }

        modelAndView.addObject("location","/student/info?sno=" + student.getNo());
        modelAndView.setViewName("common/msg");

        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView, @RequestParam String sno) {
        int result = 0;
        result = studentService.delete(sno);

        if (result > 0) {
            modelAndView.addObject("msg","학생 정보가 삭제되었습니다.");
            modelAndView.addObject("location","/student/search");
            modelAndView.setViewName("common/msg");
        } else{
            modelAndView.addObject("msg","학생 정보 삭제를 실패하였습니다.");
            modelAndView.addObject("location","/student/info?sno=" + sno);
        }

        modelAndView.setViewName("common/msg");

        return modelAndView;
    }

}