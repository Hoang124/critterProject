package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Customer;
import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.mappers.ScheduleMapper;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ScheduleMapper scheduleMapper,
                           PetRepository petRepository,
                           EmployeeRepository employeeRepository,
                           CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.petRepository = petRepository;
        this. employeeRepository = employeeRepository;
        this. customerRepository = customerRepository;
    }

    public ScheduleDTO saveOrUpdate(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.save(scheduleMapper.toEntity(scheduleDTO));
        return scheduleMapper.toDTO(schedule);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(scheduleMapper::toDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new ResourceNotFoundException("Pet Not Found"));
        return scheduleRepository.findScheduleByPets(pet)
                .stream().map(scheduleMapper::toDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return scheduleRepository.findScheduleByEmployees(employee)
                .stream().map(scheduleMapper::toDTO).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(Long customerId) {
        List<Pet> pets = petRepository.findPetsByCustomer(customerId);
        List<Schedule> allSchedules = new ArrayList<>();

        for (Pet p : pets) {
            List<Schedule> schedules = scheduleRepository.findScheduleByPets(p);
            allSchedules.addAll(schedules);
        }

        return new ArrayList<>(allSchedules.stream()
                .collect(Collectors.toMap(
                        Schedule::getId,
                        scheduleMapper::toDTO,
                        (existing, replacement) -> existing))
                .values());
    }
}
