package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleServive scheduleServive;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        // convert ScheduleDTO to Schedule
        Schedule schedule = convertScheduleDTOToSchedule(scheduleDTO);

        Schedule scheduleSaved = scheduleServive.save(schedule);

        // convert Schedule to ScheduleDTO
        ScheduleDTO scheduleDTOToReturn = convertScheduleToScheduleDTO(scheduleSaved);
        return scheduleDTOToReturn;

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleServive.findAll();
        List<ScheduleDTO> scheduleDTOS = convertScheduleToScheduleDTO(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> schedules = scheduleServive.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOS = convertScheduleToScheduleDTO(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleServive.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOS = convertScheduleToScheduleDTO(schedules);
        return scheduleDTOS;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Pet> pets = petService.findPetsByCustomer(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Pet pet : pets) {
            List<Schedule> schedules = scheduleServive.getScheduleForPet(pet.getId());
            scheduleDTOS.addAll(convertScheduleToScheduleDTO(schedules));
        }
        return scheduleDTOS;
    }

    // create convertScheduleDTOToSchedule

    public Schedule convertScheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());

        // convert employee ids to employees
        List<Employee> employees = convertEmployeeIdsToEmployees(scheduleDTO.getEmployeeIds());
        schedule.setEmployees(employees);

        // convert pet ids to pets
        List<Pet> pets = convertPetIdsToPets(scheduleDTO.getPetIds());
        schedule.setPets(pets);
        return schedule;
    }

    // create convertScheduleToScheduleDTO

    public ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());

        // convert employees to employee ids
        List<Long> employeeIds = convertEmployeesToEmployeeIds(schedule.getEmployees());
        scheduleDTO.setEmployeeIds(employeeIds);

        // convert pets to pet ids
        List<Long> petIds = convertPetsToPetIds(schedule.getPets());
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    // convert list of schedules to list of scheduleDTOs

    public List<ScheduleDTO> convertScheduleToScheduleDTO(List<Schedule> schedules) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            ScheduleDTO scheduleDTO = convertScheduleToScheduleDTO(schedule);
            scheduleDTOS.add(scheduleDTO);
        }
        return scheduleDTOS;
    }

    // convert employees ids to employees
    public List<Employee> convertEmployeeIdsToEmployees(List<Long> employeeIds) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < employeeIds.size(); i++) {
            Long employeeId = employeeIds.get(i);
            employees.add(employeeService.find(employeeId));
        }
        return employees;
    }

    // convert employees to employee ids

    public List<Long> convertEmployeesToEmployeeIds(List<Employee> employees) {
        List<Long> employeeIds = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeeIds.add(employee.getId());
        }
        return employeeIds;
    }

    // convert pets ids to pets

    public List<Pet> convertPetIdsToPets(List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < petIds.size(); i++) {
            Long petId = petIds.get(i);
            pets.add(petService.find(petId));
        }
        return pets;
    }

    // convert pets to pet ids

    public List<Long> convertPetsToPetIds(List<Pet> pets) {
        List<Long> petIds = new ArrayList<>();
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            petIds.add(pet.getId());
        }
        return petIds;
    }
}
