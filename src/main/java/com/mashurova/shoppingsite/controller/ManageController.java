package com.mashurova.shoppingsite.controller;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManageController {
    private PizzaRepository pizzaRepository;

    public ManageController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/managePizza")
    public String managePizza(Model model) {

        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 5, Sort.by("name").ascending())).getContent();
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }
}
