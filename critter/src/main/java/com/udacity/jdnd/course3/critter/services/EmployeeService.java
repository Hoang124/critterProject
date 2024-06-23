package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entites.Employee;
import com.udacity.jdnd.course3.critter.exception.ResourceNotFoundException;
import com.udacity.jdnd.course3.critter.mappers.EmployeeMapper;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeDTO saveOrUpdate(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeDTO));
        return employeeMapper.toDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        return  employeeMapper.toDTO(employee);
    }

    public void delete(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        employeeRepository.delete(employee);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> employees = employeeRepository
                .findEmployeeForService(employeeRequestDTO.getSkills(), employeeRequestDTO.getDate());
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
        return employeeDTOS;
    }
}
