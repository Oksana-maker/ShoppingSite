package com.mashurova.shoppingsite.controller;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ManageController {
    private PizzaRepository pizzaRepository;

    public ManageController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/managePizza")
    public String managePizza(Model model) {

        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 15, Sort.by("name").ascending())).getContent();
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }
    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, Model model) {
        log.info("PizzaController::addPizza()");
        log.info("Argument: {}"+ pizza);
        pizza.setImageName("pizzaFunghi.jpeg");
        pizzaRepository.save(pizza);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "managePizza";
    }
    @GetMapping("/managePizzaNew")
    public String getPizzas(Model model){
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "managePizzaNew";
    }
    @GetMapping("/editPizza")
    public String editPizza(@RequestParam Long id, Model model) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        Pizza pizza = optionalPizza.orElseThrow(() -> new IllegalArgumentException("Pizza not found"));
        model.addAttribute("pizza", pizza);
        return "editPizza";
    }
    @PostMapping("/changePizza")
    public String changePizza(Pizza pizza, Model model){
        pizzaRepository.save(pizza);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas",pizzas);
        return "managePizza";
    }
    @GetMapping("/deletePizza")
    public String  deletePizza(@RequestParam Long id, Model model){
        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 15, Sort.by("name").ascending())).getContent();
        log.info("PizzaController::deletePizza()");
        log.info("Id: "+id);
        pizzaRepository.deleteById(id);
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }
}
