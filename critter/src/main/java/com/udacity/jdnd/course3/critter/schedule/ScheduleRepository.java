package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ScheduleRepository {

    @PersistenceContext
    EntityManager entityManager;

    // Save schedule
    public Schedule save(Schedule schedule) {
        return entityManager.merge(schedule);
    }

    // find schedule by employee id
    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        return entityManager.createQuery("select s from Schedule s join s.employees e where e.id = :employeeId", Schedule.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    // find schedule by pet id
    public List<Schedule> getScheduleForPet(Long petId) {
        return entityManager.createQuery("select s from Schedule s join s.pets p where p.id = :petId", Schedule.class)
                .setParameter("petId", petId)
                .getResultList();
    }

    // find all schedules
    public List<Schedule> findAll() {
        return entityManager.createQuery("select s from Schedule s", Schedule.class)
                .getResultList();
    }
}
