package com.beyond.university.student.model.service;

import com.beyond.university.student.model.mapper.StudentMapper;
import com.beyond.university.student.model.vo.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;

    @Override
    public List<Student> getStudentsByDeptNo(String deptNo) {
        return studentMapper.selectAllByDeptNo(deptNo);
    }

    @Override
    public Student getStudentsByNo(String sno) {

        return studentMapper.selectStudentByNo(sno);
    }

    @Override
    @Transactional
    public int save(Student student) {
        int result = 0;

        if (student.getNo() != null) {
            // update
            result = studentMapper.updateStudent(student);
        } else {
            // insert
            result = studentMapper.insertStudent(student);
        }
        // if (true){
        //     throw new RuntimeException("예외 발생");
        // }
        return result;
    }

    @Override
    @Transactional
    public int delete(String sno) {
        return studentMapper.deleteStudent(sno);
    }
}
