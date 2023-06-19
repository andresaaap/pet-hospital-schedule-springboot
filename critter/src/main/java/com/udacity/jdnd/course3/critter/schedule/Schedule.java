package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
    name = "schedule_employee",
    joinColumns = @JoinColumn(name = "schedule_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToMany
    @JoinTable(
    name = "schedule_pet",
    joinColumns = @JoinColumn(name = "schedule_id"),
    inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets;
    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Schedule() {
    }

    public Schedule(List<Employee> employees, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Pet> getPets(){
        return pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
