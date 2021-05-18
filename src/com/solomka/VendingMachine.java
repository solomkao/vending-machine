package com.solomka;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.models.Purchase;
import com.solomka.services.VendingMachineService;
import com.solomka.services.VendingMachineServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VendingMachine {

    private final VendingMachineService vendingMachineService;

    public VendingMachine(int numberOfCategories) {
        this.vendingMachineService = new VendingMachineServiceImpl(numberOfCategories);
    }

    public void addCategory(final Scanner scanner) {
        System.out.println("Enter categories title: ");
        String categoryTitle = scanner.nextLine();
        System.out.println("Enter price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter quantity: ");
        long quantity = scanner.nextLong();
        addCategory(categoryTitle, price, quantity);
    }

    public void addItem(final Scanner scanner) {
        System.out.println("Enter categories title: ");
        String categoryTitle = scanner.nextLine();
        System.out.println("Enter quantity: ");
        long quantity = scanner.nextLong();
        addItem(categoryTitle, quantity);
    }

    public void purchase(final Scanner scanner) {
        System.out.println("Enter categories title: ");
        String categoryTitle = scanner.nextLine();
        scanner.reset();
        System.out.println("Enter date in format yyyy-MM-dd: ");
        String date = scanner.nextLine();
        purchase(categoryTitle, date);
    }

    public void report(final Scanner scanner) {
        System.out.println("Enter date in format yyyy-MM-dd or yyyy-MM: ");
        String date = scanner.nextLine();
        report(date);
    }

    public void addCategory(String categoryTitle, double price, long quantity) {
        try {
            this.vendingMachineService.addCategory(categoryTitle, price, quantity);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Category " + categoryTitle + " was added.");
    }

    public void addItem(String categoryTitle, long quantity) {
        try {
            this.vendingMachineService.addItem(categoryTitle, quantity);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(quantity + " items of " + categoryTitle + " were added.");
    }

    public void purchase(String categoryTitle, String date) {
        try {
            this.vendingMachineService.purchase(categoryTitle, date);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Item " + categoryTitle + " was sold");
    }

    public void list() {
        List<Category> categories = this.vendingMachineService.list();
        System.out.println("List of all categories: ");
        categories.forEach(System.out::println);
    }

    public void clear() {
        List<Category> categories = this.vendingMachineService.clear();
        System.out.println("List of cleared categories: ");
        categories.forEach(System.out::println);
    }

    public void report(String date) {
        List<Purchase> purchases;
        try {
            purchases = this.vendingMachineService.report(date);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return;
        }
        double earnings = 0.0;
        for (Purchase value : purchases) {
            earnings += value.getPrice();
        }
        Map<String, Long> map = purchases.stream()
                .collect(Collectors.groupingBy((p) -> (p.getCategoriesTitle() + " $" + p.getPrice()), Collectors.counting()));
        System.out.println("Report: ");
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("$" + earnings + " was earned.");
    }
}
