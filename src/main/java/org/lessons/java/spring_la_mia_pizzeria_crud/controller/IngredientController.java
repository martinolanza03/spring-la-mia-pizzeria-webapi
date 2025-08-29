package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = repository.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredient/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ingredient", repository.findById(id).get());
        return "ingredient/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredient/create-or-edit";
        }

        repository.save(formIngredient);

        return "redirect:/ingredient";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,
            Model model) {
        model.addAttribute("ingredient", repository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredient/create-or-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredient/create-or-edit";
        }

        repository.save(formIngredient);

        return "redirect:/ingredient";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Ingredient ingredientDelate = repository.findById(id).get();

        for (Pizza linkedPizza : ingredientDelate.getPizze()) {
            linkedPizza.getIngredients().remove(ingredientDelate);
        }

        repository.delete(ingredientDelate);

        return "redirect:/ingredient";
    }
}
