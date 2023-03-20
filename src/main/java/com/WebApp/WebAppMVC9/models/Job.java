package com.WebApp.WebAppMVC9.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Job {
    private int id;

    @NotEmpty(message = "Position should not be empty")
    @Size(min = 2, max = 50, message = "Position should be between 2 and 50 characters")
    private String position;

    @Min(value = 0, message = "Salary should be greater than 0")
    private int salary;

    public Job() {

    }

    public Job(int id, String position, int salary) {
        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}