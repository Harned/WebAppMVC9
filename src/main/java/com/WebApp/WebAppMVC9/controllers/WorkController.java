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
@RequestMapping("/work")
public class WorkController {

    private final JobDAO jobDAO;
    @Autowired
    public WorkController(JobDAO jobDAO) {
        this.jobDAO = jobDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("work", jobDAO.index());
        return "work/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("job", jobDAO.show(id));
        return "work/show";
    }

    @GetMapping("/new")
    public String newJob(@ModelAttribute("job")  Job job) {
        return "work/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("job") @Valid Job job,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "work/new";

        jobDAO.save(job);
        return "redirect:/work";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("job", jobDAO.show(id));
        return "work/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("job") @Valid Job job,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "work/edit";

        jobDAO.update(id, job);
        return "redirect:/work";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        jobDAO.delete(id);
        return "redirect:/work";
    }
}