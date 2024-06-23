package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.mappers.PetMapper;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public PetDTO saveOrUpdate(PetDTO petDTO) {
        Pet pet = petRepository.save(petMapper.toEntity(petDTO));
        return petMapper.toDTO(pet);
    }

    public PetDTO getPetById(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet Not found"));
        return petMapper.toDTO(pet);
    }

    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream().map(petMapper::toDTO).collect(Collectors.toList());
    }

    public List<PetDTO> getPetByOwner(Long ownerId) {
        return petRepository.findPetsByCustomer(ownerId).stream().map(petMapper::toDTO).collect(Collectors.toList());
    }
}
