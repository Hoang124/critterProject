package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Pet;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT p FROM Pet p WHERE customer_id = :ownerId")
    List<Pet> findPetsByCustomer(Long ownerId);
}
