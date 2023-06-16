package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EmployeeRepository {

    // Create entity manager
    @PersistenceContext
    EntityManager entityManager;

    // Save employeer
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    // Find employee by id
    public Employee find(Long id) {
        return entityManager.find(Employee.class, id);
    }
}
