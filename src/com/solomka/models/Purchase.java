package com.solomka.models;

import java.time.LocalDate;

public class Purchase {

    private final String categoriesTitle;
    private final double price;
    private final LocalDate date;

    public Purchase(String categoriesTitle, double price, LocalDate date) {
        this.categoriesTitle = categoriesTitle;
        this.price = price;
        this.date = date;
    }

    public String getCategoriesTitle() {
        return categoriesTitle;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return categoriesTitle + " $"+price + " was sold " + date;
    }
}


