package com.mashurova.shoppingsite.controller;

import com.mashurova.shoppingsite.entity.Pizza;
import com.mashurova.shoppingsite.persistence.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

//    @GetMapping("/uploadimage") public String displayUploadForm() {
//        return "imageupload/manage";
//    }
//
//    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
//        StringBuilder fileNames = new StringBuilder();
//        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//        fileNames.append(file.getOriginalFilename());
//        Files.write(fileNameAndPath, file.getBytes());
//        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
//        return "imageupload/managePizza";
//    }
    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, Model model, @RequestParam("image") MultipartFile file) throws IOException {
        log.info("PizzaController::addPizza()");
        log.info("Argument: {}"+ pizza);
        pizza.setImageName(file.getOriginalFilename());
        pizzaRepository.save(pizza);

        File f = new File("src/main/resources/static/images", file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(f.toPath(), bytes);

        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "managePizza";
    }

    @GetMapping("/managePizzaNew")
    public String getPizzas(Model model) {
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

       if(p.isPresent() ) {
           String path = "C:\\Users\\LENOVO\\Downloads\\mvc (2)\\ShoppingSite\\src\\main\\resources\\static\\images\\" + p.get().getImageName();

           try {
               Files.delete(Paths.get(path));
           }catch (IOException e){
               log.error(e.getMessage());
           }

       }


        pizzaRepository.deleteById(id);
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("pizzas", pizzas);

        return "managePizza";
    }
    @GetMapping("/buyPizza")
    public String buyPizza(@RequestParam Long id, Model model) {

        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);

        if (optionalPizza.isPresent()) {
            model.addAttribute("pizza", optionalPizza.get());
        } else {
            throw new IllegalArgumentException("Pizza not found");
        }

        return "buyPizza";
    }
    @GetMapping("/submitPayment")
    public String submitPayment(){
        return "submitPayment";
    }
}
