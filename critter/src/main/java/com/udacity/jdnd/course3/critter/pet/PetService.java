package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

        // Autowire PetRepository
        @Autowired
        PetRepository petRepository;

        @Autowired
        CustomerRepository customerRepository;

        // Save pet
        public Pet save(Pet pet) {

            Pet savedPet = petRepository.save(pet);
            Customer customer = savedPet.getCustomer();

            List<Pet> pets = customer.getPets();

            if (pets == null) {
                pets = new ArrayList<>();
            }

            pets.add(savedPet);
            customer.setPets(pets);
            customerRepository.save(customer);

            return savedPet;

        }

        // Find pet by id
        public Pet find(Long id) {
            return petRepository.find(id);
        }

        // find Pets By customer
        public List<Pet> findPetsByCustomer(Long customerId) {
            return petRepository.findPetsByCustomer(customerId);
        }

        // Find all pets
        public List<Pet> findAll() {
            return petRepository.findAll();
        }
}
