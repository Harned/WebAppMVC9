package com.WebApp.WebAppMVC9.controllers;

import com.WebApp.WebAppMVC9.dao.CustomerDAO;
import com.WebApp.WebAppMVC9.models.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class CustomerController {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", customerDAO.index());
        return "clients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("Customer", customerDAO.show(id));
        return "clients/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("Customer") Customer customer) {
        return "clients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("Customer") @Valid Customer customer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "clients/new";

        сustomerDAO.save(customer);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("Customer", сustomerDAO.show(id));
        return "clients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("Customer") @Valid Customer customer, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "clients/edit";

        CustomerDAO.update(id, customer);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        customerDAO.delete(id);
        return "redirect:/clients";
    }
}