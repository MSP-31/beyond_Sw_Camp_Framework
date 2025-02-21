package com.beyond.university.department.model.mapper;

import com.beyond.university.department.model.vo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DepartmentMapper {
    List<Department> selectAll();
}
