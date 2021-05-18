package com.solomka.models;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Double.compare(purchase.price, price) == 0 && Objects.equals(categoriesTitle, purchase.categoriesTitle) && Objects.equals(date, purchase.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriesTitle, price, date);
    }
}


