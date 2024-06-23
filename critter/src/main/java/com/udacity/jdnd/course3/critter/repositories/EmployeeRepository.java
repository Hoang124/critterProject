package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT DISTINCT e FROM Employee e JOIN e.skills es WHERE es IN (:skills) " +
            "OR EXISTS (SELECT s FROM e.schedules s WHERE s.date = :date)")
    List<Employee> findEmployeeForService(@Param("skills") Set<EmployeeSkill> skills, @Param("date") LocalDate date);
}
