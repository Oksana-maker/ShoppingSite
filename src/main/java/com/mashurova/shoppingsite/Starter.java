package com.mashurova.shoppingsite;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Starter implements CommandLineRunner {
    private PizzaRepository pizzaRepository;


    public Starter(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void run(String... args) {
        List<Pizza> pizzas = List.of(new Pizza("Valentina", "Tomato, Chicken, Parmesan, Onion", "valentina.jpeg",20.5),
                new Pizza("Veggie Pizza", "Tomato, Eggplant, Arugula, Mushrooms, Cheese", "veggie.jpeg",15.4),
                new Pizza("Meat Pizza", "Meat, Tomato, Cheese, Pepperoni", "meat.webp",20.5),
                new Pizza("Margherita Pizza", "Tomato, Mozzarella", "margherita.webp",16.9),
                new Pizza("BBQ Chicken Pizza", "Sauce BBQ, Cheese, Chicken, Onion ", "bbq.webp",20.75),
                new Pizza("Hawaiian Pizza", "Pineapple, Cheese, Chicken", "hawaiian.webp",18.9),
                new Pizza("Buffalo Pizza", "Buffalo sauce, Cheese, Chicken, Onion", "buffalo.webp",20.5),
                new Pizza("Pepperoni Pizza", "Pepperoni, Cheese, Salami", "salami.webp",19.66));
        pizzaRepository.saveAll(pizzas);

    }
}
