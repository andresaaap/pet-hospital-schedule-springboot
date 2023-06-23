package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Customer save(Customer customer) {
        return entityManager.merge(customer);
    }

    public Customer find(Long id) {
        return entityManager.find(Customer.class, id);
    }

    //find all customers
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    // create findByOwnerByPetId
    public Customer findByOwnerByPetId(Long petId) {
        return entityManager.createQuery("SELECT c FROM Customer c JOIN c.pets p WHERE p.id = :petId", Customer.class)
                .setParameter("petId", petId)
                .getSingleResult();
    }

}
