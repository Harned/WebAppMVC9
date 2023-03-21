package com.WebApp.WebAppMVC9.dao;

import com.WebApp.WebAppMVC9.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> index() {
        return jdbcTemplate.query("SELECT * FROM Product", new BeanPropertyRowMapper<>(Product.class));
    }

    public Product show(int id) {
        return jdbcTemplate.query("SELECT * FROM Product WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Product.class))
                .stream().findAny().orElse(null);
    }

    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO Product VALUES(1, ?, ?)", product.getTitle(), product.getPrice());
    }

    public void update(int id, Product updatedProduct) {
        jdbcTemplate.update("UPDATE Product SET title=?, price=? WHERE id=?", updatedProduct.getTitle(),
                updatedProduct.getPrice(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Product WHERE id=?", id);
    }
}