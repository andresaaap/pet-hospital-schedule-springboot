package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
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

    // findEmployeesForService
    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        // size of skills
        Long skillsSize = Long.valueOf(skills.size());

        // query to join the employee, employee_skills and days_available tables. The query should return the employees that have all the skills required for the service and are available on the specified day of the week.
        return entityManager.createQuery("select e from Employee e join e.skills s where :dayOfWeek member of e.daysAvailable and e.id in (select e2.id from Employee e2 join e2.skills s where s in :skills Group by e2.id HAVING COUNT(DISTINCT s) = :skillsSize)", Employee.class)
                .setParameter("skills", skills)
                .setParameter("dayOfWeek", dayOfWeek)
                .setParameter("skillsSize", skillsSize)
                .getResultList();
    }
}
