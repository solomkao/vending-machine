package com.solomka.repositories;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.models.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface VendingMachineRepo {

    void addCategory(Category category) throws InvalidDataException;

    void purchase(Category category, LocalDate date) throws InvalidDataException;

    void addItem(String categoryTitle, long quantity) throws InvalidDataException;

    List<Category> list();

    List<Category> clear();

    List<Purchase> showPurchasesByPeriod(LocalDate date);

    List<Purchase> showPurchasesByMonth(LocalDate date);
}
