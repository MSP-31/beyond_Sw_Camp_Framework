package com.beyond.university.student.model.mapper;

import com.beyond.university.student.model.vo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> selectAllByDeptNo(@Param("deptNo") String deptNo);

    Student selectStudentByNo(@Param("sno") String studentNo);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudent(@Param("sno") String sno);
}
