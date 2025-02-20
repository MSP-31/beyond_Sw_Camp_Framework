package com.beyond.university.student.model.service;

import com.beyond.university.department.model.vo.Department;
import com.beyond.university.student.model.vo.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsByDeptNo(String deptNo);

    Student getStudentsByNo(String son);

    int save(Student student);

    @Transactional
    int delete(String sno);
}
