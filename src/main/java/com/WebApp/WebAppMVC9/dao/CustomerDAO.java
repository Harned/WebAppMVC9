package com.WebApp.WebAppMVC9.dao;

import com.WebApp.WebAppMVC9.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> index() {
        return jdbcTemplate.query("SELECT * FROM Customer", new BeanPropertyRowMapper<>(Customer.class));
    }

    public Customer show(int id) {
        return jdbcTemplate.query("SELECT * FROM Customer WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Customer.class))
                .stream().findAny().orElse(null);
    }

    public void save(Customer customer) {
        jdbcTemplate.update("INSERT INTO Customer VALUES(1, ?, ?, ?, ?)", customer.getName(), customer.getAge(), customer.getNumber(),
                customer.getEmail());
    }

    public void update(int id, Customer updatedCustomer) {
        jdbcTemplate.update("UPDATE Customer SET name=?, age=?, number=?, email=? WHERE id=?", updatedCustomer.getName(),
                updatedCustomer.getAge(), updatedCustomer.getNumber(), updatedCustomer.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Customer WHERE id=?", id);
    }
}