package com.solomka.test;

import com.solomka.exceptions.InvalidDataException;
import com.solomka.models.Purchase;
import com.solomka.services.VendingMachineService;
import com.solomka.services.VendingMachineServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;


public class VendingMachineServiceTest {

    @Test(expected = InvalidDataException.class)
    public void addCategoryShouldThrowException(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("", 1.5,0);
    }
    @Test(expected = InvalidDataException.class)
    public void addCategoryShouldThrowExceptionDueToOverflowNumberOfCategories(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(1);
        vendingMachine.addCategory("Bar", 1.5,0);
        vendingMachine.addCategory("Crisps", 1.5,0);
    }

    @Test(expected = InvalidDataException.class)
    public void addItemShouldThrowException(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Bar", 1.5,0);
        vendingMachine.addItem("Bar Bar", 10);
    }

    @Test
    public void checkCategoryShouldReturnTrue(){
        VendingMachineServiceImpl vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Name", 0.5, 1);
        Assert.assertTrue(vendingMachine.checkCategory("Name"));
    }

    @Test
    public void checkCategoryShouldReturnFalse(){
        VendingMachineServiceImpl vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Name", 0.5, 1);
        Assert.assertFalse(vendingMachine.checkCategory("Some name"));
    }

    @Test
    public void listShouldReturnEmptyList(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        Assert.assertTrue(vendingMachine.list().isEmpty());
    }

    @Test
    public void listShouldReturnFilledList(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Cracker", 0.5, 1);
        Assert.assertFalse(vendingMachine.list().isEmpty());
    }

    @Test
    public void clearShouldReturnEmptyList(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Cracker", 0.5, 1);
        Assert.assertTrue(vendingMachine.clear().isEmpty());
    }

    @Test
    public void clearShouldReturnFilledList(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Cracker", 0.5, 0);
        Assert.assertFalse(vendingMachine.clear().isEmpty());
    }

    @Test(expected = InvalidDataException.class)
    public void reportShouldThrowException(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.report("2021");
    }

    @Test
    public void reportShouldReturnFilledList(){
        VendingMachineService vendingMachine = new VendingMachineServiceImpl(5);
        vendingMachine.addCategory("Bar", 2.5, 2);
        vendingMachine.purchase("Bar", "2021-05-05");
        Purchase purchase = new Purchase("Bar", 2.5, LocalDate.of(2021, 5, 5));
        Assert.assertTrue(vendingMachine.report("2021-05-05").contains(purchase));
        vendingMachine.report("2021-05-05");
    }

}
