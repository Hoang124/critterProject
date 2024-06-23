package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByPets(Pet pet);
}
