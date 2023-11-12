package com.mashurova.shoppingsite.controller;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class ManageController {
    private final HttpSession session;
    private PizzaRepository pizzaRepository;

    public ManageController(PizzaRepository pizzaRepository, HttpSession session) {
        this.pizzaRepository = pizzaRepository;
        this.session = session;
    }

    @GetMapping("/managePizza")
    public String managePizza(Model model) {

        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 15, Sort.by("name").ascending())).getContent();
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, Model model, @RequestParam("image") MultipartFile file) throws IOException {
        log.info("PizzaController::addPizza()");
        log.info("Argument: {}" + pizza);

        if (file.isEmpty()) {
            pizza.setImageName("img_5.png");
        } else {
            pizza.setImageName(file.getOriginalFilename());
            try {
                File f = new File("src/main/resources/static/images", file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                Files.write(f.toPath(), bytes);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        pizzaRepository.save(pizza);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "managePizza";
    }


    @GetMapping("/editPizza")
    public String editPizza(@RequestParam Long id, Model model) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        Pizza pizza = optionalPizza.orElseThrow(() -> new IllegalArgumentException("Pizza not found"));
        model.addAttribute("pizza", pizza);
        return "editPizza";
    }

    @PostMapping("/changePizza")
    public String changePizza(Pizza pizza, Model model) {
        pizzaRepository.save(pizza);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "managePizza";
    }

    @GetMapping("/deletePizza")
    public String deletePizza(@RequestParam Long id, Model model) throws IOException {
        List<Pizza> pizzas = pizzaRepository.findAll(PageRequest.of(0, 15, Sort.by("name").ascending())).getContent();
        log.info("PizzaController::deletePizza()");
        log.info("Id: " + id);
        Optional<Pizza> p = pizzaRepository.findById(id);
        pizzaRepository.deleteById(id);
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }

    @GetMapping("/buyPizza")
    public String buyPizza(@RequestParam Long id, Model model, HttpSession session) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);

        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            model.addAttribute("pizza", pizza);

            session.setAttribute("orderedPizzaName", pizza.getName());
            session.setAttribute("orderedPizzaPrice", pizza.getPrice());

            session.setAttribute("orderedPizzaTotalPrice", pizza.getPrice());
            session.setAttribute("orderedPizzaQuantity", 1);

            return "buyPizza";
        } else {
            throw new IllegalArgumentException("Пицца не найдена");
        }
    }


    @GetMapping("/submitPayment")
    public String submitPayment(Model model, HttpSession session) {
        String orderedPizzaName = (String) session.getAttribute("orderedPizzaName");
        Double orderedPizzaPrice = (Double) session.getAttribute("orderedPizzaPrice");
        Double orderedPizzaTotalPrice = (Double) session.getAttribute("orderedPizzaTotalPrice");
        Integer orderedPizzaQuantity = (Integer) session.getAttribute("orderedPizzaQuantity");

        if (orderedPizzaName != null && orderedPizzaPrice != null && orderedPizzaTotalPrice != null && orderedPizzaQuantity != null) {
            model.addAttribute("name", orderedPizzaName);
            model.addAttribute("quantity", orderedPizzaQuantity);
            model.addAttribute("totalPrice", orderedPizzaTotalPrice);
        }
        return "submitPayment";
    }
}