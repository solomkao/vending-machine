package com.solomka.repositories;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.models.Purchase;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VendingMachineRepoInMemImpl implements VendingMachineRepo {
    private final List<Category> categories = new ArrayList<>();
    private final Map<Category, List<LocalDate>> sales = new HashMap<>();

    private final List<Purchase> purchases = new ArrayList<>();
    private final int maxCapacity;
    private final static int DAY_FORMATTER_SIZE = 10;
    private final static int MONTH_FORMATTER_SIZE = 7;


    public VendingMachineRepoInMemImpl(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void addCategory(Category category) throws InvalidDataException {
        if (this.categories.size() + 1 > this.maxCapacity) {
            throw new InvalidDataException("The vending machine is full.");
        }
        categories.add(category);
    }

    @Override
    public void addItem(String categoryTitle, long quantity) throws InvalidDataException {
        for (Category value : this.categories) {
            if (value.getTitle().equals(categoryTitle)) {
                value.increaseQuantity(quantity);
            }
        }
    }

    @Override
    public void purchase(Category category, LocalDate date) throws InvalidDataException {
        for (Category value : categories) {
            if (value.equals(category)) {
                value.decreaseQuantity(1);
                recordPurchase(value, date);
            }
        }
    }

    private void recordPurchase(Category soldItem, LocalDate date) {
        Purchase purchase = new Purchase(soldItem.getTitle(), soldItem.getPrice(), date);
        this.purchases.add(purchase);
    }

    @Override
    public List<Category> list() {
        return new ArrayList<>(this.categories);
    }

    @Override
    public List<Category> clear() {
        Predicate<Category> isOutOfStock = category -> (category.getQuantity() == 0);
        List<Category> clearedCategories = this.categories.stream().filter(isOutOfStock).collect(Collectors.toList());
        this.categories.removeIf(isOutOfStock);
        return clearedCategories;
    }

    @Override
    public List<Purchase> showPurchasesByPeriod(final LocalDate localDate) {
        List<Purchase> resultList = new ArrayList<>();
        for (Purchase value : this.purchases) {
            if (value.getDate().isEqual(localDate) || value.getDate().isAfter(localDate)) {
                resultList.add(value);
            }
        }
        return resultList;
    }

    @Override
    public List<Purchase> showPurchasesByMonth(final LocalDate localDate) {
        List<Purchase> resultList = new ArrayList<>();
        for (Purchase value : this.purchases) {
            if (value.getDate().getMonth().equals(localDate.getMonth())) {
                resultList.add(value);
            }
        }
        return resultList;
    }
}
