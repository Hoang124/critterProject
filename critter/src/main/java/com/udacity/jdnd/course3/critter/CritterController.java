package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.PetController;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.ScheduleController;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.UserController;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Dummy controller class to verify installation success. Do not use for
 * your project work.
 */
@RestController
public class CritterController {
    @GetMapping("/test")
    public String test(){return "Critter Starter installed successfully";}
}
