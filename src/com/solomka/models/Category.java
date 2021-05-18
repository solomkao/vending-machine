package com.solomka.models;

import com.solomka.exceptions.InvalidDataException;

import java.util.Objects;

public class Category implements Comparable<Category> {

    private String title;

    private double price;

    private long quantity;

    private long maxCapacity;

    public Category(long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Category() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws InvalidDataException {
        if (title == null || title.isBlank()) {
            throw new InvalidDataException("Invalid data");
        }
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidDataException {
        if (price <= 0) {
            throw new InvalidDataException("The price can not be 0 or less.");
        }
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) throws InvalidDataException {
        if (quantity < 0 || quantity > maxCapacity) {
            throw new InvalidDataException("The quantity can not be less than 0.");
        }
        this.quantity = quantity;
    }

    public long getMaxCapacity() {
        return maxCapacity;
    }

    public void increaseQuantity(long quantity) throws InvalidDataException {
        if (quantity <= 0) {
            throw new InvalidDataException("Invalid data");
        }
        if ((this.quantity + quantity) > this.maxCapacity) {
            throw new InvalidDataException("Invalid data");
        }
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) throws InvalidDataException {
        if (quantity <= 0) {
            throw new InvalidDataException("Invalid data");
        }
        if ((this.quantity - quantity) < 0) {
            throw new InvalidDataException("Invalid data");
        }
        this.quantity -= quantity;
    }

    @Override
    public int compareTo(Category o) {
        return title.compareTo(o.getTitle());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


    @Override
    public String toString() {
        return "Category [" + title +" $" + price+ ", " + quantity + " items are available. Max capacity is "+ maxCapacity +
                ']';
    }
}
