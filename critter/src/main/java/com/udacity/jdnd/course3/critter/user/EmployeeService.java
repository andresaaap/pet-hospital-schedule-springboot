package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Set;

@Service
public class EmployeeService {

    // Autowire EmployeeRepository
    @Autowired
    EmployeeRepository employeeRepository;

    // Save employee
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Find employee by id
    public Employee find(Long id) {
        return employeeRepository.find(id);
    }

    // setAvailability of employee
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository.find(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }
}
