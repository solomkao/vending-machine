package com.solomka.services;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.models.Purchase;
import com.solomka.repositories.VendingMachineRepo;
import com.solomka.repositories.VendingMachineRepoInMemImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineRepo vendingMachineRepository;

    private final static int DAY_FORMATTER_SIZE = 10;

    private final static int MONTH_FORMATTER_SIZE = 7;

    private final static long maxCategoriesCapacity = 25;

    public VendingMachineServiceImpl(int numberOfCategories) {
        this.vendingMachineRepository = new VendingMachineRepoInMemImpl(numberOfCategories);
    }

    @Override
    public void addCategory(String categoryTitle, double price, long quantity) throws InvalidDataException {
        Category category = new Category(maxCategoriesCapacity);
        category.setTitle(categoryTitle);
        category.setPrice(price);
        category.setQuantity(quantity);
        vendingMachineRepository.addCategory(category);
    }

    @Override
    public void addItem(String categoryTitle, long quantity) throws InvalidDataException {
        if (checkCategory(categoryTitle.trim())) {
            vendingMachineRepository.addItem(categoryTitle, quantity);
        } else {
            throw new InvalidDataException("This item does not fit into available categories.");
        }
    }

    @Override
    public void purchase(String categoryTitle, String date) throws InvalidDataException {
        LocalDate dateOfPurchase = LocalDate.parse(date);
        if (checkCategory(categoryTitle.trim())) {
            Category category = new Category();
            category.setTitle(categoryTitle);
            vendingMachineRepository.purchase(category, dateOfPurchase);
        } else {
            throw new InvalidDataException("There is no such category.");
        }
    }

    private boolean checkCategory(String categoryTitle) {
        List<Category> categories = vendingMachineRepository.list();
        for (Category category : categories) {
            if (category.getTitle().equalsIgnoreCase(categoryTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Category> list() {
        List<Category> categories = vendingMachineRepository.list();
        Collections.sort(categories);
        return categories;
    }

    @Override
    public List<Category> clear() {
        List<Category> categories = vendingMachineRepository.clear();
        Collections.sort(categories);
        return categories;
    }

    @Override
    public List<Purchase> report(String date) throws InvalidDataException {
        List<Purchase> purchases = null;
        if (date.trim().length() == DAY_FORMATTER_SIZE) {
            LocalDate dateOfPurchase = LocalDate.parse(date);
            purchases = vendingMachineRepository.showPurchasesByPeriod(dateOfPurchase);
        }
        if (date.trim().length() == MONTH_FORMATTER_SIZE) {
            LocalDate dateOfPurchase = LocalDate.parse(date + "-01");
            purchases = vendingMachineRepository.showPurchasesByMonth(dateOfPurchase);
        }

        if (purchases == null || purchases.isEmpty()) {
            throw new InvalidDataException("Nothing was sold on " + date);
        }
        return purchases;
    }
}
