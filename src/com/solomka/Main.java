package com.solomka;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Category;
import com.solomka.services.VendingMachineService;
import com.solomka.services.VendingMachineServiceImpl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static VendingMachine vendingMachine;


    public static void main(String[] args) {
        int numberOfCategories = 5;
        vendingMachine = new VendingMachine(numberOfCategories);
        menu();
    }

    private static void menu(){
        String[] operations = new String[]{
                "1. Register a snack category in the system.",
                "2. Register provided amount of snack items to sell.",
                "3. Purchase a single snack item.",
                "4. Show list of served categories with amount of items.",
                "5. Clear list of snack categories that don’t have items to sale.",
                "6. Show earnings by category.",
                "0. Exit"
        };
        System.out.println("Enter the corresponding number:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (String op : operations) {
                System.out.println(op);
            }
            switch (scanner.nextLine()){
                case "1": vendingMachine.addCategory(scanner);
                    break;
                case "2": vendingMachine.addItem(scanner);
                    break;
                case "3": vendingMachine.purchase(scanner);
                    break;
                case "4": vendingMachine.list();
                    break;
                case "5": vendingMachine.clear();
                    break;
                case "6": vendingMachine.report(scanner);
                    break;
                case "0":
                    System.out.println("See you later.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Incorrect number! Try again!");
            }
        }
    }


}
