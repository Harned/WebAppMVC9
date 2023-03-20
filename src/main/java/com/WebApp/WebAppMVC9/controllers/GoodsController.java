package com.WebApp.WebAppMVC9.controllers;

import com.WebApp.WebAppMVC9.dao.ProductDAO;
import com.WebApp.WebAppMVC9.models.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final ProductDAO productDAO;
    @Autowired
    public GoodsController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("goods", productDAO.index());
        return "goods/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productDAO.show(id));
        return "goods/show";
    }

    @GetMapping("/new")
    public String newJob(@ModelAttribute("product")  Product product) {
        return "goods/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "goods/new";

        productDAO.save(product);
        return "redirect:/goods";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("product", productDAO.show(id));
        return "goods/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "goods/edit";

        productDAO.update(id, product);
        return "redirect:/goods";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productDAO.delete(id);
        return "redirect:/goods";
    }
}