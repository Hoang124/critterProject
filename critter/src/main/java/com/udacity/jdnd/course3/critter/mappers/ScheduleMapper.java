package com.udacity.jdnd.course3.critter.mappers;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import com.udacity.jdnd.course3.critter.entites.User;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {
    @Autowired
    PetRepository petRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public ScheduleDTO toDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());
        if (schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(User::getId).collect(Collectors.toList()));
        }
        if (schedule.getPets() != null) {
            scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return scheduleDTO;
    }

    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());
        if (scheduleDTO.getEmployeeIds() != null) {
            List<Employee> employees = new ArrayList<>();
            for (Long employeeId : scheduleDTO.getEmployeeIds()) {
                employeeRepository.findById(employeeId).ifPresent(employees::add);
            }
            schedule.setEmployees(employees);
        }
        if (scheduleDTO.getPetIds() != null) {
            List<Pet> pets = new ArrayList<>();
            for (Long petId : scheduleDTO.getPetIds()) {
                petRepository.findById(petId).ifPresent(pets::add);
            }
            schedule.setPets(pets);
        }
        return schedule;
    }
}
