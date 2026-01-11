package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.repository.DepartmentRepository;
import com.moldavets.finalproject.model.Department;
import com.moldavets.finalproject.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllOrderByIdAsc() {
        return departmentRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Department> getAllOrderByIdDesc() {
        return departmentRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Department> getAllOrderByAbbreviationAsc() {
        return departmentRepository.findAllByOrderByAbbreviationAsc();
    }

    @Override
    public List<Department> getAllOrderByAbbreviationDesc() {
        return departmentRepository.findAllByOrderByAbbreviationDesc();
    }

    @Override
    public List<Department> getAllOrderByNameAsc() {
        return departmentRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Department> getAllOrderByNameDesc() {
        return departmentRepository.findAllByOrderByNameDesc();
    }

    @Override
    @Transactional
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    @Transactional
    public void delete(int departmentId) {
        if(departmentRepository.existsById(departmentId)) {
            departmentRepository.deleteById(departmentId);
        }
    }

}
