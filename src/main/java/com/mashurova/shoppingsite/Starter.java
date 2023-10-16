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
        List<Pizza> pizzas = List.of(new Pizza("Fungh", "Tomato, Cheese", "pizzaFunghi.jpeg",20.5),
                new Pizza("Tuna", "Tomato, Fish", "tun.jpeg",18.9),
                new Pizza("Funghi1", "Tomato, Cheese", "pizzaFunghi.jpeg",20.5),
                new Pizza("Tuna1", "Tomato, Fish", "tun.jpeg",18.9),
                new Pizza("Funghi2", "Tomato, Cheese", "pizzaFunghi.jpeg",20.5),
                new Pizza("Tuna2", "Tomato, Fish", "tun.jpeg",18.9),
                new Pizza("Funghi3", "Tomato, Cheese", "pizzaFunghi.jpeg",20.5),
                new Pizza("Tuna4", "Tomato, Fish", "tun.jpeg",18.9));
        pizzaRepository.saveAll(pizzas);

    }
}
