package com.WebApp.WebAppMVC9.dao;

import com.WebApp.WebAppMVC9.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Job> index() {
        return jdbcTemplate.query("SELECT * FROM Job", new BeanPropertyRowMapper<>(Job.class));
    }

    public Job show(int id) {
        return jdbcTemplate.query("SELECT * FROM Job WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Job.class))
                .stream().findAny().orElse(null);
    }

    public void save(Job job) {
        jdbcTemplate.update("INSERT INTO Job VALUES(1, ?, ?)", job.getPosition(), job.getSalary());
    }

    public void update(int id, Job updatedJob) {
        jdbcTemplate.update("UPDATE Job SET position=?, salary=? WHERE id=?", updatedJob.getPosition(),
                updatedJob.getSalary(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Job WHERE id=?", id);
    }
}