package com.moldavets.finalproject.service.Impl;

import com.moldavets.finalproject.repository.EmployeeRepository;
import com.moldavets.finalproject.model.Employee;
import com.moldavets.finalproject.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Employee> getAllOrderByIdAsc() {
        return employeeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Employee> getAllOrderByIdDesc() {
        return employeeRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Employee> getAllByOrderByFirstNameAsc() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public List<Employee> getAllByOrderByFirstNameDesc() {
        return employeeRepository.findAllByOrderByFirstNameDesc();
    }

    @Override
    public List<Employee> getAllByOrderByLastNameAsc() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public List<Employee> getAllByOrderByLastNameDesc() {
        return employeeRepository.findAllByOrderByLastNameDesc();
    }

    @Override
    public List<Employee> getAllByOrderByDepartmentAsc() {
        return employeeRepository.findAllByOrderByDepartmentAsc();
    }

    @Override
    public List<Employee> getAllByOrderByDepartmentDesc() {
        return employeeRepository.findAllByOrderByDepartmentDesc();
    }

    @Override
    public List<Employee> getAllByOrderByBirthdayAsc() {
        return employeeRepository.findAllByOrderByBirthdayAsc();
    }

    @Override
    public List<Employee> getAllByOrderByBirthdayDesc() {
        return employeeRepository.findAllByOrderByBirthdayDesc();
    }

    @Override
    public List<Employee> getAllByInputString(String query) {
        return employeeRepository.findAllByInputString("%" + query + "%");
    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }
    }

}
