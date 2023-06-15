package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Long id;
    private PetType type;
    private String name;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    private String birthDate;
    private String notes;

    @ManyToMany
    @JoinTable(
    name = "schedule_pet",
    joinColumns = @JoinColumn(name = "pet_id"),
    inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private List<Schedule> schedules;

    public Pet() {
    }

    public Pet(PetType type, String name, Customer customer, String birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.customer = customer;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public PetType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

}
