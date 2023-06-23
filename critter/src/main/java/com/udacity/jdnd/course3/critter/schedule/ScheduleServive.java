package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServive {

        // Autowire ScheduleRepository
        @Autowired
        ScheduleRepository scheduleRepository;

        // Save schedule
        public Schedule save(Schedule schedule) {
            return scheduleRepository.save(schedule);
        }

        // find schedule by employee id
        public List<Schedule> getScheduleForEmployee(Long employeeId) {
            List<Schedule> schedules = scheduleRepository.getScheduleForEmployee(employeeId);
            return schedules;
        }

        // find schedule by pet id
        public List<Schedule> getScheduleForPet(Long petId) {
            List<Schedule> schedules = scheduleRepository.getScheduleForPet(petId);
            return schedules;
        }

        // find all schedules
        public List<Schedule> findAll() {
            List<Schedule> schedules = scheduleRepository.findAll();
            return schedules;
        }

}
