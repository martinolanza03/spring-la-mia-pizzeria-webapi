package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "special_offer")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The start offer date cannot be null")
    private LocalDate startOffer;

    @NotNull(message = "The start offer date cannot be null")
    private LocalDate endOffer;

    @NotNull(message = "The title date cannot be null")

    private String title;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public Integer getId() {
        return this.id;
    }

    public SpecialOffer() {
    }

    public SpecialOffer(Integer id, LocalDate startOffer, LocalDate endOffer, Pizza pizza) {
        this.id = id;
        this.startOffer = startOffer;
        this.endOffer = endOffer;
        this.pizza = pizza;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartOffer() {
        return this.startOffer;
    }

    public void setStartOffer(LocalDate startOffer) {
        this.startOffer = startOffer;
    }

    public LocalDate getEndOffer() {
        return this.endOffer;
    }

    public void setEndOffer(LocalDate endOffer) {
        this.endOffer = endOffer;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
