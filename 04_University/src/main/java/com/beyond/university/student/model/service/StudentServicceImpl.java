package com.beyond.university.student.model.service;

import com.beyond.university.student.model.mapper.StudentMapper;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServicceImpl implements StudentServicce {
    private final StudentMapper studentMapper;

    @Override
    public List<Student> getStudentsByDeptNo(String deptNo) {
        return studentMapper.selectAllByDeptNo(deptNo);
    }
}
