package com.mashurova.shoppingsite.controller;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    private PizzaRepository pizzaRepository;

    public HomeController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("PizzaController::index()");
        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 5, Sort.by("name").ascending())).getContent();
        model.addAttribute("pizzas", pizzas);

        return "index";
    }
    @GetMapping("/private")
    public String privatePage() {
        return "private";
    }

}
