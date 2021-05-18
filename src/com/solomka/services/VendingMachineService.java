package com.solomka.services;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.models.Purchase;

import java.util.List;

public interface VendingMachineService {

    void addCategory(String categoryTitle, double price, long quantity) throws InvalidDataException;

    void addItem(String categoryTitle, long quantity) throws InvalidDataException;

    void purchase(String categoryTitle, String date) throws InvalidDataException;

    List<Category> list();

    List<Category> clear();

    List<Purchase> report(String date) throws InvalidDataException;
}
