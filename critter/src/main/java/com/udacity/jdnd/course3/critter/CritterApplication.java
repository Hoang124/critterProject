package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.PetController;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.schedule.ScheduleController;
import com.udacity.jdnd.course3.critter.user.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

/**
 * Launches the Spring application. Unmodified from starter code.
 */
@SpringBootApplication
public class CritterApplication {

	public static void main(String[] args) {SpringApplication.run(CritterApplication.class, args);}
}
