package com.WebApp.WebAppMVC9.controllers;

import com.WebApp.WebAppMVC9.dao.JobDAO;
import com.WebApp.WebAppMVC9.models.Job;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/works")
public class WorksController {

    private final JobDAO jobDAO;
    @Autowired
    public WorksController(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("works", jobDAO.index());
        return "works/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("job", jobDAO.show(id));
        return "works/show";
    }

    @GetMapping("/new")
    public String newJob(@ModelAttribute("job")  Job job) {
        return "works/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("job") @Valid Job job,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "works/new";

        jobDAO.save(job);
        return "redirect:/works";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("job", jobDAO.show(id));
        return "works/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("job") @Valid Job job,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "works/edit";

        jobDAO.update(id, job);
        return "redirect:/works";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        jobDAO.delete(id);
        return "redirect:/works";
    }
}