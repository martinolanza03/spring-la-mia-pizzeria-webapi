package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> findByTitle(String name) {
        return pizzaRepository.findByTitleContainIgnoreCase(name);
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);
        return pizzaAttempt.get();
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delate(Pizza pizza) {
        pizzaRepository.delete(pizza);
    }

    public void delateById(Integer id) {
        Pizza pizza = pizzaRepository.findById(id).get();

        pizzaRepository.delete(pizza);
    }
}
