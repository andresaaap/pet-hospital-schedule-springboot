package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer find(Long id) {
        return customerRepository.find(id);
    }

    // find all customers
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // create findByOwnerByPetId
    public Customer findByOwnerByPetId(Long petId) {
        return customerRepository.findByOwnerByPetId(petId);
    }
}
