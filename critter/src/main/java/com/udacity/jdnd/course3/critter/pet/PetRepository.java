package com.udacity.jdnd.course3.critter.pet;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PetRepository {

    //Create entity manager
    @PersistenceContext
    EntityManager entityManager;

    //Save pet
    public Pet save(Pet pet) {
        return entityManager.merge(pet);
    }

    //Find pet by id
    public Pet find(Long id) {
        return entityManager.find(Pet.class, id);
    }

    // find Pets By customer
    public List<Pet> findPetsByCustomer(Long customerId) {
        return entityManager.createQuery("select p from Pet p where p.customer.id = :customerId", Pet.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
