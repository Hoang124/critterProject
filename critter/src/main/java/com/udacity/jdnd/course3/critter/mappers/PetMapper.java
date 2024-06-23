package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    @Autowired
    CustomerRepository customerRepository;

    public PetDTO toDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setType(pet.getType());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    public Pet toEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setType(petDTO.getType());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        Customer customer = customerRepository.findById(petDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        pet.setCustomer(customer);
        return pet;
    }
}
