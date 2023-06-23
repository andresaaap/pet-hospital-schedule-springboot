package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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

    // findEmployeesForService
    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return employeeRepository.findEmployeesForService(skills, dayOfWeek);
    }
}
