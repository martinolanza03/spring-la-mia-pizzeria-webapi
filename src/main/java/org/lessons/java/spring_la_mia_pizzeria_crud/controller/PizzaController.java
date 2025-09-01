package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Pizza> pizze = pizzaService.findAll();
        model.addAttribute("pizze", pizze);
        model.addAttribute("username", authentication.getName());
        return "pizze/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizze/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/create";
        }

        pizzaService.create(formPizza);

        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,
            Model model) {

        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizze/edit";
        }

        pizzaService.update(formPizza);

        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delate(@PathVariable("id") Integer id) {
        pizzaService.delateById(id);

        return "redirect:/pizze";
    }

    @GetMapping("/{id}/offer")
    public String offer(@PathVariable("id") Integer id,
            Model model) {

        SpecialOffer offer = new SpecialOffer();

        offer.setPizza(pizzaService.getById(id));

        model.addAttribute("offer", offer);

        return "offer/create-or-edit";
    }

}
