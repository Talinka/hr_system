package com.hr_system;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
    private @Id @GeneratedValue long id;
    private String name;
    private int age;
    private String position;

    Employee() {}

    Employee(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public  String getPosition() {
        return this.position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}