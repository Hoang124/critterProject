package com.udacity.jdnd.course3.critter.entites;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {
    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "skill")
    private Set<EmployeeSkill> skills;
    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "day_of_week", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "day_available")
    private Set<DayOfWeek> daysAvailable;
    @ManyToMany(mappedBy = "employees")
    private List<Schedule> schedules;

    public Employee() {
    }

    public Employee(String name, Long id, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, List<Schedule> schedules) {
        super(name, id);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
        this.schedules = schedules;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
