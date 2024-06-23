package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    PetRepository petRepository;

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        if (customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setNotes(customerDTO.getNotes());

        if (customerDTO.getPetIds() != null) {

            List<Pet> pets = new ArrayList<>();
            for(long id : customerDTO.getPetIds()) {
                petRepository.findById(id).ifPresent(pets::add);
            }
            customer.setPets(pets);
        }

        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }
}
