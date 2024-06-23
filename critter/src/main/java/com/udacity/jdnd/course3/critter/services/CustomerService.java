package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.mappers.CustomerMapper;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    PetRepository petRepository;

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO saveOrUpdate(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerMapper.toEntity(customerDTO));
        return customerMapper.toDTO(customer);
    }

    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return customerMapper.toDTO(customer);
    }

    public CustomerDTO getOwnerByPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet Not Found"));
        Customer customer = customerRepository.findCustomerByPets(pet)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return customerMapper.toDTO(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        customerRepository.delete(customer);
    }
}
