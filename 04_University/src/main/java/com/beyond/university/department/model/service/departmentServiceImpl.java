package com.beyond.university.department.model.service;

import com.beyond.university.department.model.mapper.DepartmentMapper;
import com.beyond.university.department.model.vo.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class departmentServiceImpl implements DepartmentService{
    private final DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDeartments() {
        return departmentMapper.selectAll();
    }
}
