package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.entites.Pet;
import com.udacity.jdnd.course3.critter.entites.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByPets(Pet pet);
    List<Schedule> findScheduleByEmployees(Employee employee);
}
