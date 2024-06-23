package com.udacity.jdnd.course3.critter.entites;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_gen")
    @TableGenerator(name = "user_table_gen", table = "sequence_table", pkColumnName = "sequence_name", valueColumnName = "sequence_value", pkColumnValue = "user_sequence", allocationSize = 1)
    private Long id;
    private String name;

    public User() {
    }

    public User(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

